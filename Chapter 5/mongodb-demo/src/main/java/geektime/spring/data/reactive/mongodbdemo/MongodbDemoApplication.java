package geektime.spring.data.reactive.mongodbdemo;

import geektime.spring.data.reactive.mongodbdemo.converter.MoneyReadConverter;
import geektime.spring.data.reactive.mongodbdemo.converter.MoneyWriteConverter;
import geektime.spring.data.reactive.mongodbdemo.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@SpringBootApplication
@Slf4j
public class MongodbDemoApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(MongodbDemoApplication.class, args);
	}

	@Autowired
	private ReactiveMongoTemplate mongoTemplate;

	private CountDownLatch cdl = new CountDownLatch(2);

	/**
	 * 初始化 Converter
	 */
	@Bean
	public MongoCustomConversions mongoCustomConversions() {
		return new MongoCustomConversions(Arrays.asList(new MoneyReadConverter(), new MoneyWriteConverter()));
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
//		startFromInsertion(() -> log.info("Runnable"));
		startFromInsertion(() -> {
			log.info("Runnable");
			decreaseHighPrice();
		});

		log.info("after starting"); // 最先打印，比 "Runnable" 还早

//		decreaseHighPrice(); // 如果直接解开这里的注释，这里会比 startFromInsertion 先执行

		cdl.await();
	}

	private void startFromInsertion(Runnable runnable) {
		mongoTemplate.insertAll(initCoffee())
				.publishOn(Schedulers.elastic()) // 整个操作在 elastic 线程池操作
				.doOnNext(c -> log.info("Next: {}", c)) // 每次执行之后都打印一下
				.doOnComplete(runnable) // 完成时执行（会先打印 runnable 里的 log "Runnable" 再是 "Finally"）
				.doFinally(s -> {
					cdl.countDown();
					log.info("Finally 1, {}", s);
				})
				.count() // doFinally 返回 Flux 对象，这里对该对象计数
				.subscribe(c -> log.info("Insert {} records", c)); // 会比 "Finally" 先打印
	}

	private void decreaseHighPrice() {
		mongoTemplate.updateMulti(
						query(where("price").gte(3000L)),
						new Update().inc("price", -500L).currentDate("updateTime"),
						Coffee.class)
				.doFinally(s -> {
					cdl.countDown();
					log.info("Finally 2, {}", s); // 如果是在 startFromInsertion 中执行，会先打印 Finally 1
				})
				.subscribe(r -> log.info("Result is {}", r));
	}

	private List<Coffee> initCoffee() {
		Coffee espresso = Coffee.builder()
				.name("espresso")
				.price(Money.of(CurrencyUnit.of("CNY"), 20.0))
				.createTime(new Date())
				.updateTime(new Date())
				.build();
		Coffee latte = Coffee.builder()
				.name("latte")
				.price(Money.of(CurrencyUnit.of("CNY"), 30.0))
				.createTime(new Date())
				.updateTime(new Date())
				.build();

		return Arrays.asList(espresso, latte);
	}
}
