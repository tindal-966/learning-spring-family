package geektime.spring.data.druiddemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class FooService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void selectForUpdate() {
        jdbcTemplate.queryForObject("select id from foo where id = 1 for update", Long.class); // for update 会使用行锁

        try {
            Thread.sleep(200); // 锁 200 毫秒
        } catch (InterruptedException e) { }
    }
}
