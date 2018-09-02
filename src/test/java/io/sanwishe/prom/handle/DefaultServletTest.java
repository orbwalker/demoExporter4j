package io.sanwishe.prom.handle;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assumptions.assumingThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DefaultServletTest {

    @ParameterizedTest
    @ValueSource(strings = {"/metrics", "/", ""})
    void doGet(String path) throws Exception {

        assumingThat(path.isEmpty(), () -> {
            System.out.println("path is empty");
        });

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        when(resp.getWriter()).thenReturn(printWriter);

        new DefaultServlet(path).doGet(req, resp);

        System.out.println(writer.toString());
    }
}
