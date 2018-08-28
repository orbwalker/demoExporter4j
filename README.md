# demeExporter4j
Demo exporter of prometheus that wrote with java.

# Description
对于某些只能通过Java获取指标，如果需要通过prometheus exporter暴露metrics，需要使用java编写exporter。

golang中提供了`net/http`包可以很简单的对外提供http server。JDK中没有提供http server服务，所以为了对外暴露http接口，我们需要进入Spring、jetty、tomcat等类似的http server服务。考虑到系统最小化，我们可以选择jetty-server来启动http server。

prometheus的client_java中，提供metrics最小依赖包括：`simpleclient`和`simpleclient_servlet`。

综上，maven配置如下：

```xml

```
