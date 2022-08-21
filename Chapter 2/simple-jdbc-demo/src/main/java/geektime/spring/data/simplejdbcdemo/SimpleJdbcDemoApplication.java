package geektime.spring.data.simplejdbcdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;

@SpringBootApplication
@Slf4j
public class SimpleJdbcDemoApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(SimpleJdbcDemoApplication.class, args);
    }

    /**
     * 创建 SimpleJdbcInsert Bean，简化插入操作
     */
    @Bean
    @Autowired
    public SimpleJdbcInsert simpleJdbcInsert(JdbcTemplate jdbcTemplate) {
        return new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("FOO").usingGeneratedKeyColumns("ID");
    }
    /**
     * 创建 NamedParameterJdbcTemplate Bean，简化 CRUD 的赋值操作
     */
    @Bean
    @Autowired
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Autowired
    private FooDao fooDao;
    @Autowired
    private BatchFooDao batchFooDao;
    @Override
    public void run(String... args) throws Exception {
        fooDao.insertData();
        batchFooDao.batchInsert();
        fooDao.listData();
    }
}

