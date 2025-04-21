package top.caodong0225.mq.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.caodong0225.mq.entity.Orders;
import top.caodong0225.mq.store.InMemoryMessageStore;

import java.util.UUID;

@Service
public class MessageService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private InMemoryMessageStore messageStore;

    public String sendTo(String queueName, Orders orders) {
        String messageId = UUID.randomUUID().toString();
        orders.setOrderStatus("PENDING");
        orders.setOrderId(messageId);
        messageStore.put(messageId, orders);

        try {
            rabbitTemplate.convertAndSend(queueName, orders);
            orders.setOrderStatus("SENT");
            messageStore.update(messageId, orders);
            System.out.println("消息发送成功: " + messageId);
        } catch (Exception e) {
            orders.setOrderStatus("FAILED");
            messageStore.update(messageId, orders);
            System.err.println("消息发送失败: " + messageId);
        }

        return messageId; // 返回消息ID，前端或其他服务可以根据它查状态
    }
}