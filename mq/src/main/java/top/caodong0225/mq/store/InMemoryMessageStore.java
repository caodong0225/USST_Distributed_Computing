package top.caodong0225.mq.store;

import org.springframework.stereotype.Component;
import top.caodong0225.mq.entity.Orders;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryMessageStore {

    // key = messageId，value = 状态
    private final Map<String, Orders> messageStatusMap = new ConcurrentHashMap<>();

    public void put(String messageId, Orders orders) {
        messageStatusMap.put(messageId, orders);
    }

    public Orders get(String messageId) {
        return messageStatusMap.getOrDefault(messageId, null);
    }

    public Map<String, Orders> getAll() {
        return messageStatusMap;
    }

    public void update(String messageId, Orders orders) {
        messageStatusMap.put(messageId, orders);
    }

    public void remove(String messageId) {
        messageStatusMap.remove(messageId);
    }
}

