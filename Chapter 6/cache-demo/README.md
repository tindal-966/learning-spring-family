# Cache Demo
- 使用返回类型 ResponseEntity 来设置缓存
    ```java
    return ResponseEntity.ok()
            .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS)) // 设置缓存
            .body(coffee);
    ```
- static resource 配置（位置+缓存时间）
