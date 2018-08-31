## 概述

对Java程序员而言，JUnit 4无疑是使用最广泛的单元测试框架。自2006年初JUnit 4发布之后，最新的4.12版本是在2014年底发布的。终于在11年后，JUnit 5在2017年10月份正式发布了。
JUnit 5的主要目标之一，就是使JUnit更易于做进一步的提高和改进。然而在JUnit 4时期，大量未内部构建而添加的接口被外部扩展和构建工具的开发人员违规使用，这使得JUnit 4很难改进。为解决该问题，JUnit 5中引入了一种用于所有公开接口、类和方法的预定生命周期。

JUnit 5由三个子项目组成，分别是JUnit Platform、JUnit Jupiter和Junit Vintage。

- JUnit Platform定义了开发测试框架的TestEngine API,该API可用于开发在平台上运行的测试框架，JUnit Platform是加载测试框架的基础。
- JUnit Jupiter由新的编程模型和扩展（Extension）模型组成，用于编写JUnit 5中的测试和扩展。
- JUnit Vintage提供了运行JUnit 3和JUnit 4测试所用的TestEngine。

根据用户指南，在JUnit 5中引入了一种称为“Launcher”的概念。Launcher用于发现、过滤和执行测试。第三方软件库可以提供定制的TestEngine方式，以插件形式加入JUnit Platform的Launcher架构中。
JUnit 5在运行时需要Java 8及以上版本。

# 使用配置

## 依赖简介

### JUnit Platform

- Group ID ： `org.junit.platform`
- Version : `1.2.0`
- Artifact IDs :

#### `junit-platform-commons`

JUnit 内部通用类库/实用工具，它们仅用于JUnit框架本身，不支持任何外部使用。

#### `junit-platform-console`


