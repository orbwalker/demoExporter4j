package io.sanwishe.prom;

import io.sanwishe.prom.handle.DefaultServlet;
import io.sanwishe.prom.handle.MetricsServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.net.InetSocketAddress;

public class GbaseExporter {
    public static void main(String[] args) {
        // create http server via jetty server
        Server server = new Server(InetSocketAddress.createUnresolved("0.0.0.0", 9296));

        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.setContextPath("/");
        contextHandler.addServlet(new ServletHolder(new DefaultServlet("/metrics")), "/");
        contextHandler.addServlet(new ServletHolder(new MetricsServlet()), "/metrics");

        server.setHandler(contextHandler);

//        DefaultExports.initialize();

        // Start the webserver.
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            server.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
