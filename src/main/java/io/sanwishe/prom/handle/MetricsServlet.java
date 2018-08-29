package io.sanwishe.prom.handle;

import io.sanwishe.prom.metrics.Collector;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MetricsServlet extends HttpServlet {

    private final CollectorRegistry registry;

    public MetricsServlet() {
        this.registry = CollectorRegistry.defaultRegistry;
    }

    public void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType(TextFormat.CONTENT_TYPE_004);

        new Collector().collect();

        Writer writer = resp.getWriter();
        try {
            TextFormat.write004(writer, registry.filteredMetricFamilySamples(parse(req)));
            writer.flush();
        } finally {
            writer.close();
        }
    }

    private Set<String> parse(HttpServletRequest req) {
        String[] includedParam = req.getParameterValues("name[]");
        if (includedParam == null) {
            return Collections.emptySet();
        } else {
            return new HashSet<String>(Arrays.asList(includedParam));
        }
    }
}
