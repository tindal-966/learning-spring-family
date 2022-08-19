# Command Line Demo
编写命令行运行的程序 Demo

方式有三种：
- 控制依赖：不添加 Web 相关依赖（但为了使用 web 有关类的比较麻烦）
- 配置方式：spring.main.web-application-type=none
- 编程方式：
    - SpringApplication：setWebApplicationType()
    - SpringApplicationBuilder：web()
    - 在调用 SpringApplication 的 run() 方法前设置 WebApplicationType
