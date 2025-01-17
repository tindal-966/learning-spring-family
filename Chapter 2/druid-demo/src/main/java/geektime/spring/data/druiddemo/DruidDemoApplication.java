package geektime.spring.data.druiddemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
@Slf4j
public class DruidDemoApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(DruidDemoApplication.class, args);
	}

	@Autowired
	private DataSource dataSource;
	@Override
	public void run(String... args) throws Exception {
		log.info(dataSource.toString());
	}
}

