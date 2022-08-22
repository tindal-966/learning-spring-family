package geektime.spring.springbucks;

import geektime.spring.springbucks.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@EnableTransactionManagement
@SpringBootApplication
@EnableJpaRepositories
@EnableCaching(proxyTargetClass = true) // 开启缓存（也是基于 AOP 实现）
public class SpringBucksApplication implements ApplicationRunner {
	@Autowired
	private CoffeeService coffeeService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBucksApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("Count: {}", coffeeService.findAllCoffee().size());
		for (int i = 0; i < 10; i++) {
			log.info("Reading from cache.");
			coffeeService.findAllCoffee(); // 这里可以看到 log 不再打印 SQL
		}

		coffeeService.reloadCoffee(); // 手动缓存过期
		log.info("Reading after refresh.");
		coffeeService.findAllCoffee().forEach(c -> log.info("Coffee {}", c.getName()));
	}
}

