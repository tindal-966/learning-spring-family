# Druid Demo
Druid 连接池 Demo

[Druid连接池介绍](https://github.com/alibaba/druid/wiki/Druid%E8%BF%9E%E6%8E%A5%E6%B1%A0%E4%BB%8B%E7%BB%8D)

Druid 主要依赖 FilterChain 来实现一系列的功能。例如，监控使用 StatFilter，日志使用 LogFilter，更多内置 Filter [参考](https://github.com/alibaba/druid/wiki/%E5%86%85%E7%BD%AEFilter%E7%9A%84%E5%88%AB%E5%90%8D) 。

可以自定义 Filter，继承 com.alibaba.druid.filter.FilterEventAdapter 即可
