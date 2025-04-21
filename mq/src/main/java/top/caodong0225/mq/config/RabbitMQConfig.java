package top.caodong0225.mq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_GOODS = "queueGoods";
    public static final String QUEUE_ORDERS = "queueOrders";

    @Bean
    public Queue queueGoods() {
        return new Queue(QUEUE_GOODS, true);
    }

    @Bean
    public Queue queueOrders() {
        return new Queue(QUEUE_ORDERS, true);
    }
}
