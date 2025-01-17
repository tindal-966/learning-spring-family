package geektime.spring.springbucks.waiter.integration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderListener {
    @StreamListener(Barista.FINISHED_ORDERS) // 监听 Channel，执行方法
    public void listenFinishedOrders(Long id) {
        log.info("We've finished an order [{}].", id);
    }
}
