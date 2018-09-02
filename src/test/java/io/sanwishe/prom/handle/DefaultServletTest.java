package io.sanwishe.prom.handle;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DefaultServletTest {

    @ParameterizedTest
    @ValueSource(strings = {"/metrics", "/", ""})
    void doGet(String path) throws Exception {

        assumeTrue(!path.isEmpty(), () -> "path must not be empty");

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        when(resp.getWriter()).thenReturn(printWriter);

        new DefaultServlet(path).doGet(req, resp);

        assertTrue(resp.getStatus() == HttpStatus.OK_200, () -> "status must be ok");
        assertTrue(!writer.toString().isEmpty(), () -> "response message should not be empty");
    }

    @Test
    void assumeTest() {
        System.out.println(System.getProperty("os.name"));
        assumingThat(System.getProperty("os.name").startsWith("Mac OS"), () -> {
            System.out.println("You are working on a macOS host.");
        });
    }
}
