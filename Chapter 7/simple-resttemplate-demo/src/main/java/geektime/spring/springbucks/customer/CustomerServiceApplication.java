package geektime.spring.springbucks.customer;

import geektime.spring.springbucks.customer.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;

@SpringBootApplication
@Slf4j
public class CustomerServiceApplication implements ApplicationRunner {
	@Autowired
	private RestTemplate restTemplate;

	public static void main(String[] args) {
		// SpringApplication.run(CustomerServiceApplication.class, args); // 一般情况下

		new SpringApplicationBuilder()
				.sources(CustomerServiceApplication.class)
				.web(WebApplicationType.NONE) // 主要是这里，不会以 Tomcat 形式启动（SERVICE 才会）（这里非 web 却引入 web 一是为了使用有关的类，二是为了使用自动配置的 RestTemplateBuilder）
				.run(args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
//		return new RestTemplate();
		return builder.build(); // 入参的 builder 来自类 RestTemplateAutoConfiguration
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:8080/coffee/{id}")
				.build(1);
		ResponseEntity<Coffee> c = restTemplate.getForEntity(uri, Coffee.class);
		log.info("Response Status: {}, Response Headers: {}", c.getStatusCode(), c.getHeaders().toString());
		log.info("Coffee: {}", c.getBody());

		String coffeeUri = "http://localhost:8080/coffee/";
		Coffee request = Coffee.builder()
				.name("Americano")
				.price(BigDecimal.valueOf(25.00))
				.build();
		Coffee response = restTemplate.postForObject(coffeeUri, request, Coffee.class);
		log.info("New Coffee: {}", response);

		String s = restTemplate.getForObject(coffeeUri, String.class);
		log.info("String: {}", s);
	}
}
