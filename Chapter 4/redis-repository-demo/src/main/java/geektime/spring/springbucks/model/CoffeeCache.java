package geektime.spring.springbucks.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

/**
 * Redis 持久化 Model 类
 */
@RedisHash(value = "springbucks-coffee", timeToLive = 60) // 一定要定义失效时间
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CoffeeCache {
    @Id
    private Long id;

    @Indexed
    private String name;

    private Money price;
}
