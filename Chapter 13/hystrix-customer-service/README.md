# Hystrix Customer Service
使用 Hystrix 实现 method-class, class-level 的 fallback 处理

- method-level: @HystrixCommand(fallbackMethod = "methodName")
- class-level: @FeignClient(fallback = xxx.class)
