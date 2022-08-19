# AutoConfigure Demo
自定义 AutoConfigure 项目的使用 Demo

测试项目：
- 直接运行，可以看到打印 greeting 项目里面的 log
- application.properties 中 greeting.enabled 改为 false，看不到 log
- pom.xml 中注释 greeting 依赖，看不到 log
- 取消 AutoconfigureDemoApplication 类中 @Bean 的注释，看到的 log 内容是自己 new 出来的内容
- 添加 `--debug` 参数，分别重复进行 1、2、4 项查看自动启动的内容。
  - 第 1 项可以在 Positive matches 看到 GreetingAutoConfiguration；
  - 第 2 项可以在 Negative matches 中看到不符合 greeting.enabled=true 的条件
  - 第 4 项可以在 Negative matches 中看到不符合 @ConditionalOnMissingBean

[Docs-2.1.1.RELEASE](https://docs.spring.io/spring-boot/docs/2.1.1.RELEASE/reference/html/boot-features-developing-auto-configuration.html#boot-features-developing-auto-configuration) & [Demo](https://github.com/snicoll/spring-boot-master-auto-configuration)
