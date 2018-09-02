package io.sanwishe.prom.metrics;

import org.junit.jupiter.api.Test;

class CollectorTest {

    @Test
    void collect() {
        Collector collector = new Collector();
        collector.collect();
    }
}
