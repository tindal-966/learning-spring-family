package geektime.spring.springbucks.jpademo.repository;

import geektime.spring.springbucks.jpademo.model.CoffeeOrder;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeOrderRepository extends CrudRepository<CoffeeOrder, Long> { // 这里的 CurdRepository 可以替换为 PagingAndSortingRepository （有分页方法）或者 JpaRepository（更常用）
}
