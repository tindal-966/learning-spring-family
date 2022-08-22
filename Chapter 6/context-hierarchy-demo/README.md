# Context Hierarchy Demo
父子层次的 ApplicationContext 切面各自为政的情况

applicationContext.xml 中的 `<aop:aspectj-autoproxy/>` 等同于 @EnableAspectJAutoProxy，表示在每个 ApplicationContext 中都声明一次允许 AspectJ 的自动代理

参考 [EnableAspectJAutoProxy](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/annotation/EnableAspectJAutoProxy.html) 里面提到了：
- Enables support for handling components marked with AspectJ's @Aspect annotation, similar to functionality found in Spring's <aop:aspectj-autoproxy> XML element.
- **Note: @EnableAspectJAutoProxy applies to its local application context only, allowing for selective proxying of beans at different levels. Please redeclare @EnableAspectJAutoProxy in each individual context, e.g. the common root web application context and any separate DispatcherServlet application contexts, if you need to apply its behavior at multiple levels.**
