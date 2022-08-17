package geektime.demo;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 参考：https://docs.spring.io/spring-boot/docs/2.1.1.RELEASE/reference/html/boot-features-testing.html#boot-features-testing-spring-boot-applications-testing-with-running-server
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloWorldApplicationTests {
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testHelloController() {
		String body = this.restTemplate.getForObject("/hello", String.class);
		Assertions.assertThat(body).isEqualTo("Hello World!");
	}
}

