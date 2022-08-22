package geektime.spring.data.mongodemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document // 指明为 MongoDB 的 Doc
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Coffee {
    @Id // 这里的 ID 是 org.springframework.data.annotation.Id，RDBMS 中常用 javax.persistence.Id
    private String id;
    private String name;
    private Money price;
    private Date createTime;
    private Date updateTime;
}
