package geektime.spring.data.mongodemo.repository;

import geektime.spring.data.mongodemo.model.Coffee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * 使用 MongoRepository 来操作 MongoDB
 */
public interface CoffeeRepository extends MongoRepository<Coffee, String> {
    List<Coffee> findByName(String name);
}
