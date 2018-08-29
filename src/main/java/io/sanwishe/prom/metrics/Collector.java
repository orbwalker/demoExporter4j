package io.sanwishe.prom.metrics;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Histogram;
import io.prometheus.client.Summary;

import java.util.Random;

public class Collector {
    private final static String NAME_SPACE = "gbase";
    private final static String SUB_SYSTEM = "business";

    private final static Gauge GAUGE = Gauge.build()
            .namespace(NAME_SPACE)
            .subsystem(SUB_SYSTEM)
            .name("up")
            .help("Example metrics for testing")
            .labelNames("version", "node")
            .register();
    private final static Counter COUNTER = Counter.build()
            .namespace(NAME_SPACE)
            .subsystem(SUB_SYSTEM)
            .name("scrape_total")
            .help("Example metrics for testing")
            .labelNames("node")
            .register();
    private final static Histogram HISTOGRAM = Histogram.build()
            .namespace(NAME_SPACE)
            .subsystem(SUB_SYSTEM)
            .name("web_request_elapsed")
            .help("Example histogram for testing")
            .labelNames("node")
            .buckets(0.3, 0.5, 1.0, 3.0, 5.0)
            .register();

    private final static Summary SUMMARY = Summary.build()
            .namespace(NAME_SPACE)
            .subsystem(SUB_SYSTEM)
            .name("db_query_elapsed")
            .help("Example summary for testing")
            .quantile(0.5, 0.01)
            .quantile(0.9, 0.01)
            .labelNames("node")
            .register();


    public void collect() {
        GAUGE.labels("8a", "10.62.127.88").set(1.0);
        COUNTER.labels("10.62.127.88").inc();
        HISTOGRAM.labels("10.62.127.88").observe(new Random().nextDouble() % 10.0);
        SUMMARY.labels("10.62.127.88").observe(new Random().nextDouble());
    }
}
