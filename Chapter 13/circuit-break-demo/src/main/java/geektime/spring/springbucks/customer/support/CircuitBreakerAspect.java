package geektime.spring.springbucks.customer.support;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Aspect
@Component
@Slf4j
public class CircuitBreakerAspect {
    private static final Integer THRESHOLD = 3;
    private Map<String, AtomicInteger> errorCounter = new ConcurrentHashMap<>(); // 失败次数
    private Map<String, AtomicInteger> breakCounter = new ConcurrentHashMap<>(); // 断路保护次数

    @Around("execution(* geektime.spring.springbucks.customer.integration..*(..))") // 拦截 Feign 的接口
    public Object doWithCircuitBreaker(ProceedingJoinPoint pjp) throws Throwable {
        String signature = pjp.getSignature().toLongString(); // 方法签名
        log.info("Invoke {}", signature);

        Object retVal;
        try {
            if (errorCounter.containsKey(signature)) {
                if (errorCounter.get(signature).get() > THRESHOLD &&
                        breakCounter.get(signature).get() < THRESHOLD) { // 当 breakCounter 大于默认值的话会尝试自动恢复
                    log.warn("Circuit breaker return null, break {} times.",
                            breakCounter.get(signature).incrementAndGet());
                    return null;
                }
            } else {
                errorCounter.put(signature, new AtomicInteger(0));
                breakCounter.put(signature, new AtomicInteger(0));
            }
            retVal = pjp.proceed();
            errorCounter.get(signature).set(0);
            breakCounter.get(signature).set(0);
        } catch (Throwable t) {
            log.warn("Circuit breaker counter: {}, Throwable {}",
                    errorCounter.get(signature).incrementAndGet(), t.getMessage());
            breakCounter.get(signature).set(0);
            throw t;
        }
        return retVal;
    }
}
