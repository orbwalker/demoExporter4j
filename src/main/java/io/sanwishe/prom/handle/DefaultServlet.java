package io.sanwishe.prom.handle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DefaultServlet extends HttpServlet {

    private static final String linkTextFormatter = "<html>\n<head><title>Postgres exporter</title></head>\n<body>\n<h1>Postgres Exporter</h1><p><a href='%s'>Metrics</a></p></body>\n</html>";
    private final String metricsPath;

    public DefaultServlet(String metricsPath) {
        this.metricsPath = metricsPath;
    }

    public void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print(String.format(linkTextFormatter, this.metricsPath));
    }
}
