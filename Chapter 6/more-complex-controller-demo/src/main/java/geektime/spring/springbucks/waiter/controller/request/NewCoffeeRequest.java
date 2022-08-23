package geektime.spring.springbucks.waiter.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.money.Money;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class NewCoffeeRequest {
    @NotEmpty // 不允许为空
    private String name;

    @NotNull // 不允许为 null
    private Money price;
}
