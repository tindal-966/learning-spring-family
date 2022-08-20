package geektime.spring.springbucks.customer.support;

import geektime.spring.springbucks.customer.model.CoffeeOrder;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

/**
 * 监听的事件
 */
@Data
public class OrderWaitingEvent extends ApplicationEvent {
    private CoffeeOrder order;

    public OrderWaitingEvent(CoffeeOrder order) {
        super(order);
        this.order = order;
    }
}
