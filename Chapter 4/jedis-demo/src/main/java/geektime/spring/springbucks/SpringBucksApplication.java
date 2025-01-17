package geektime.spring.springbucks;

import geektime.spring.springbucks.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;

@Slf4j
@EnableTransactionManagement
@SpringBootApplication
@EnableJpaRepositories
public class SpringBucksApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBucksApplication.class, args);
	}

	@Autowired
	private CoffeeService coffeeService;
	@Autowired
	private JedisPool jedisPool; // 这里注入 JedisPool
	@Autowired
	private JedisPoolConfig jedisPoolConfig; // 这里注入 JedisPoolConfig

	/**
	 * 初始化 JedisPoolConfig Bean
	 */
	@Bean
	@ConfigurationProperties("redis") // 使用配置文件中 redis 开头的配置
	public JedisPoolConfig jedisPoolConfig() {
		return new JedisPoolConfig();
	}
	/**
	 * 初始化 JedisPool Bean
	 */
	@Bean(destroyMethod = "close")
	public JedisPool jedisPool(@Value("${redis.host}") String host) {
		return new JedisPool(jedisPoolConfig(), host); // 这里用了上面初始化的 JedisPoolConfig
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info(jedisPoolConfig.toString());

		try (Jedis jedis = jedisPool.getResource()) { // 从 jedisPool 获取资源来操作 Redis
			coffeeService.findAllCoffee().forEach(c -> {
				jedis.hset("springbucks-menu",
						c.getName(),
						Long.toString(c.getPrice().getAmountMinorLong()));
			});

			Map<String, String> menu = jedis.hgetAll("springbucks-menu");
			log.info("Menu: {}", menu);

			String price = jedis.hget("springbucks-menu", "espresso");
			log.info("espresso - {}",
					Money.ofMinor(CurrencyUnit.of("CNY"), Long.parseLong(price)));
		}
	}
}

