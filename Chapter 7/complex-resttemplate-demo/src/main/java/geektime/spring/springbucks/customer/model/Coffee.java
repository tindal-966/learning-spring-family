package geektime.spring.springbucks.customer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coffee implements Serializable {
    private Long id;
    private String name;
    private Money price; // 这里使用了 Money 类型，在 JSON Serializer/Deserializer 时需要做额外的处理，见 support 包
    private Date createTime;
    private Date updateTime;
}
