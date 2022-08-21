# Pure Spring datasource Demo
仅使用 Spring （不使用 SpringBoot）的数据源 Demo

注意非 Spring-Boot-based 项目下的 Spring 使用：
1. 在 Main 方法中创建 Spring 的 ApplicationContext （需要引入依赖 spring-context 以及创建 xml 配置文件）
2. 在 xml 配置文件中声明 component-scan 的 base-package 来扫描 Spring 有关的 Bean

**非 Spring-Boot-based 项目打出来的 Jar 包不能直接执行（Maven 结构的项目默认配置都是不能直接执行），参考 pom.xml 中的 plugins 配置**

疑惑：打包不知道为什么不能识别 `new ClassPathXmlApplicationContext("applicationContext*.xml");` 带星号的 applicationContext*.xml 改成不带星就正常了
