package geektime.spring.springbucks.barista;

import geektime.spring.springbucks.barista.integration.Waiter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
@EnableBinding(Waiter.class) // 声明绑定的 Channel
public class BaristaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaristaServiceApplication.class, args);
	}

}
