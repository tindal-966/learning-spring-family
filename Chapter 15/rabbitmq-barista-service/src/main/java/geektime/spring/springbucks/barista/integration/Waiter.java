package geektime.spring.springbucks.barista.integration;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Spring Cloud Stream Channel 名（实际 RabbitMQ 队列名）
 */
public interface Waiter {
    String NEW_ORDERS = "newOrders";
    String FINISHED_ORDERS = "finishedOrders";

    @Input(NEW_ORDERS)
    SubscribableChannel newOrders();

    @Output(FINISHED_ORDERS)
    MessageChannel finishedOrders();
}
