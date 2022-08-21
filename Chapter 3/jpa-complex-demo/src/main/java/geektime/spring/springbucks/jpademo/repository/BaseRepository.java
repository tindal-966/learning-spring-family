package geektime.spring.springbucks.jpademo.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

@NoRepositoryBean // 注意这个注解
public interface BaseRepository<T, Long> extends PagingAndSortingRepository<T, Long> {
    List<T> findTop3ByOrderByUpdateTimeDescIdAsc();
}
