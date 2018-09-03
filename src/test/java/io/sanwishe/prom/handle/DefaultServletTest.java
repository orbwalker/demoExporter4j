package io.sanwishe.prom.handle;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DefaultServletTest {

    private static Stream<String> paramProvider() {
        return Stream.of("/metrics", "/", "");
    }

    private static String[] paramProviderWithArray() {
        return new String[]{"/metrics", "/"};
    }

    @BeforeEach
    void before() {
        System.out.println("before each.");
    }

    @ParameterizedTest
//    @ValueSource(strings = {"/metrics", "/", ""})
//    @MethodSource("paramProvider")
//    @MethodSource("paramProviderWithArray")
    @CsvSource({"/metrics", "/"})
//    @CsvFileSource(resources = "/param.csv")
    void doGet(String path) throws Exception {

        assumeTrue(!path.isEmpty(), () -> "path must not be empty");

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        when(resp.getWriter()).thenReturn(printWriter);

        new DefaultServlet(path).doGet(req, resp);

//        assertTrue(resp.getStatus() == HttpStatus.OK_200, () -> "status must be ok");
        assertTrue(!writer.toString().isEmpty(), () -> "response message should not be empty");
    }

    @TestFactory
    Stream<DynamicTest> doGetTest() {
        return Stream.of("/metrics", "/", "")
                .map(path -> DynamicTest.dynamicTest("test" + path, () -> {
                    assumeTrue(!path.isEmpty(), () -> "input path must not be empty");

                    HttpServletRequest req = mock(HttpServletRequest.class);
                    HttpServletResponse resp = mock(HttpServletResponse.class);
                    StringWriter writer = new StringWriter();
                    PrintWriter printWriter = new PrintWriter(writer);
                    when(resp.getWriter()).thenReturn(printWriter);

                    new DefaultServlet(path).doGet(req, resp);

                    assertTrue(!writer.toString().isEmpty(), "response message should not be empty");
                }));
    }

    @Test
    @Tag("slow")
    void assumeTest() {
        System.out.println(System.getProperty("os.name"));
        assumingThat(System.getProperty("os.name").startsWith("Mac OS"), () -> {
            System.out.println("You are working on a macOS host.");
        });
    }

    @Test
    @EnabledOnOs(OS.LINUX)
    void conditionTest() {
        assertTrue(System.getProperty("os.name").startsWith("Windows"));
    }
}
