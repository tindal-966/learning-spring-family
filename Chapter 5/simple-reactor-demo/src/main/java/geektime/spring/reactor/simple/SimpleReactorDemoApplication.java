package geektime.spring.reactor.simple;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@SpringBootApplication
@Slf4j
public class SimpleReactorDemoApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(SimpleReactorDemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Flux.range(1, 6) // 创建 1~6 的序列
				.doOnRequest(n -> log.info("Request {} number", n)) // 注意顺序造成的区别
//				.publishOn(Schedulers.elastic()) // 解开注释的话：会在 elastic 线程池中执行下面内容
				.doOnComplete(() -> log.info("Publisher COMPLETE 1"))
				.map(i -> {
					log.info("Publish {}, {}", Thread.currentThread(), i); // 演示序列中的每个值在哪个线程执行（都在主线程）
//					return 10 / (i - 3);
					return i;
				})
				.doOnComplete(() -> log.info("Publisher COMPLETE 2"))
//				.subscribeOn(Schedulers.single())
//				.onErrorResume(e -> { // 解开注释（配合 return 10 / (i - 3);）的话：publish 3 能正常 subscribe 出 -1 之前显示日志
//					log.error("Exception {}", e.toString());
//					return Mono.just(-2); // 即使同时解开 .onErrorReturn(-1) 也会返回 -2
//				})
//				.onErrorReturn(-1) // 解开注释（配合 return 10 / (i - 3);）的话：publish 3 能正常 subscribe 出 -1，但不会往下执行
				.subscribe(i -> log.info("Subscribe {}: {}", Thread.currentThread(), i),
						e -> log.error("error {}", e.toString()),
						() -> log.info("Subscriber COMPLETE")//,
//						s -> s.request(4)
				);
		Thread.sleep(2000);
	}
}

