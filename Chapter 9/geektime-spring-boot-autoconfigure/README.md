# GeekTime Spring Boot Autoconfigure
SpringBoot AutoConfigure Demo

实现 AutoConfig 主要工作：
1. 编写 Java Config @Configuration
2. 添加条件 @Conditional
3. 定位自动配置 META-INF/spring.factories

前两步都在类 GreetingAutoConfiguration 中

第三步在 resources 资源中，这里可以参考依赖 spring-boot-autoconfigure 的里面的 resources/META-INF/spring.factories
