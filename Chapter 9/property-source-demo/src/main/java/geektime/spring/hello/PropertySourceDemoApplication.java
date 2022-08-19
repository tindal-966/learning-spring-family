package geektime.spring.hello;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@Slf4j
@PropertySource("yapf2.properties") // 也可以通过 @PropertySource 添加
public class PropertySourceDemoApplication implements ApplicationRunner {
	@Value("${geektime.greeting}")
	private String greeting;

	@Value("${geektime.another}")
	private String another;

	public static void main(String[] args) {
		SpringApplication.run(PropertySourceDemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("{} {}", greeting, another);
	}
}
