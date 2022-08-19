# Jar Demo
[Docs](https://docs.spring.io/spring-boot/docs/2.1.1.RELEASE/reference/html/executable-jar.html)

打包可以直接使用 ./xxx.jar 执行的 jar 包，只需要在 spring-boot-maven-plugin 中指定 `<executable>true</executable>` 即可
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <executable>true</executable>
            </configuration>
        </plugin>
    </plugins>
</build>
```

此时要设置运行时参数需要可以在 .conf 的同名文件（和 Jar 包同名）中配置，参考 [waiter-service.conf](./waiter-service.conf)
