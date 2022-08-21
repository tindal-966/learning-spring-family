package geektime.spring.data.declarativetransactiondemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement(mode = AdviceMode.PROXY) // 这里使用的代理 PROXY 的模式（必须要有接口），还有 ASPECTJ 模式
@Slf4j
public class DeclarativeTransactionDemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DeclarativeTransactionDemoApplication.class, args);
	}

	@Autowired
	private FooService fooService;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... args) throws Exception {
		fooService.insertRecord();
		log.info("AAA {}",
				jdbcTemplate.queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='AAA'", Long.class));

		try {
			fooService.insertThenRollback();
		} catch (Exception e) {
			log.info("BBB {}",
					jdbcTemplate.queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='BBB'", Long.class));
		}

		try {
			fooService.invokeInsertThenRollback(); // 这里会发生事务失效：因为 invokeInsertThenRollback() 直接调用类内方法，没有走代理之后的方法（Spring 的事务管理依赖代理增强事务注解标记的方法/类）
		} catch (Exception e) {
			log.info("BBB {}",
					jdbcTemplate.queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='BBB'", Long.class));
		}
	}
}

