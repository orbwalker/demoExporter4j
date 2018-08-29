# demeExporter4j
Demo exporter of prometheus that wrote with java.

# Description
对于某些只能通过Java获取指标，如果需要通过prometheus exporter暴露metrics，需要使用java编写exporter。

golang中提供了`net/http`包可以很简单的对外提供http server。JDK中没有提供http server服务，所以为了对外暴露http接口，我们需要进入Spring、jetty、tomcat等类似的http server服务。考虑到系统最小化，我们可以选择jetty-server来启动http server。

prometheus的client_java中，提供metrics最小依赖包括：`simpleclient`和`simpleclient_servlet`。

启动后，暴露的metrics格式如下：

```
# HELP gbase_business_up Example metrics for testing
# TYPE gbase_business_up gauge
gbase_business_up{version="8a",node="10.62.127.88",} 1.0
# HELP gbase_business_scrape_total Example metrics for testing
# TYPE gbase_business_scrape_total counter
gbase_business_scrape_total{node="10.62.127.88",} 25.0
# HELP gbase_business_web_request_elapsed Example histogram for testing
# TYPE gbase_business_web_request_elapsed histogram
gbase_business_web_request_elapsed_bucket{node="10.62.127.88",le="0.3",} 9.0
gbase_business_web_request_elapsed_bucket{node="10.62.127.88",le="0.5",} 11.0
gbase_business_web_request_elapsed_bucket{node="10.62.127.88",le="1.0",} 25.0
gbase_business_web_request_elapsed_bucket{node="10.62.127.88",le="3.0",} 25.0
gbase_business_web_request_elapsed_bucket{node="10.62.127.88",le="5.0",} 25.0
gbase_business_web_request_elapsed_bucket{node="10.62.127.88",le="+Inf",} 25.0
gbase_business_web_request_elapsed_count{node="10.62.127.88",} 25.0
gbase_business_web_request_elapsed_sum{node="10.62.127.88",} 12.956883155095506
# HELP gbase_business_db_query_elapsed Example summary for testing
# TYPE gbase_business_db_query_elapsed summary
gbase_business_db_query_elapsed{node="10.62.127.88",quantile="0.5",} 0.4216878825472836
gbase_business_db_query_elapsed{node="10.62.127.88",quantile="0.9",} 0.7042739834658541
gbase_business_db_query_elapsed_count{node="10.62.127.88",} 25.0
gbase_business_db_query_elapsed_sum{node="10.62.127.88",} 10.689219059224211
```
