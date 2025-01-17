package geektime.spring.data.druiddemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@SpringBootApplication
@Slf4j
@EnableTransactionManagement(proxyTargetClass = true)
public class DruidDemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DruidDemoApplication.class, args);
	}

	@Autowired
	private DataSource dataSource;
	@Autowired
	private FooService fooService;
	@Override
	public void run(String... args) throws Exception {
		log.info(dataSource.toString());

		new Thread(() -> fooService.selectForUpdate()).start(); // selectForUpdate 方法行锁 + 休眠
		new Thread(() -> fooService.selectForUpdate()).start();
	}
}

