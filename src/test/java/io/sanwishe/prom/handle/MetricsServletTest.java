package io.sanwishe.prom.handle;

import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.common.TextFormat;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MetricsServletTest {

    private Gauge exampleGauge = Gauge.build().name("test_gauge").help("help info").create();

    @BeforeAll
    public void init() {
        this.exampleGauge.register();
    }

    @Test
    @DisplayName("success branch")
    void testDoGetSuccess() throws ServletException, IOException {
        // given
        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getParameterValues(anyString())).thenReturn(new String[]{"test_gauge"});
        HttpServletResponse resp = mock(HttpServletResponse.class);

        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        when(resp.getWriter()).thenReturn(printWriter);

        // when
        new MetricsServlet().doGet(req, resp);

        // then
//        assertTrue(writer.toString().contains("test_gauge 0.0"));
//        assertTrue(resp.getStatus() == HttpStatus.OK_200, () -> "status must be ok");
//        assertTrue(TextFormat.CONTENT_TYPE_004.equals(resp.getContentType()), () -> "response type must be prometheus text format: v0.0.4");

        assertAll("check all result:",
                () -> assertTrue(writer.toString().contains("test_gauge 0.0"))/*,
                () -> assertTrue(resp.getStatus() == HttpStatus.OK_200, () -> "status must be ok"),
                () -> assertTrue(TextFormat.CONTENT_TYPE_004.equals(resp.getContentType()), () -> "response type must be prometheus text format: v0.0.4")
       */ );
    }


    @Test
    @DisplayName("wrong branch")
    void testCloseWriter() throws Exception {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        PrintWriter writer = mock(PrintWriter.class);
        when(resp.getWriter()).thenReturn(writer);

        new MetricsServlet().doGet(req, resp);
        verify(writer).close();
    }

}
