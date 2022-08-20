package geektime.spring.springbucks.waiter.integration;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Spring Cloud Stream Channel 名（实际 RabbitMQ 队列名）
 */
public interface Barista {
    String NEW_ORDERS = "newOrders";
    String FINISHED_ORDERS = "finishedOrders";

    @Input // 这里不给名字就用函数名，上面定义的常量为了方便其他地方引用，和函数名同名（建议绑定起来，避免改函数名忘改常量名）
    SubscribableChannel finishedOrders(); // 订阅 Channel

    @Output(NEW_ORDERS) // 指定名
    MessageChannel newOrders(); // 发送 Channel
}
