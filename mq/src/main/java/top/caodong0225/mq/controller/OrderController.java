package top.caodong0225.mq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.caodong0225.mq.entity.Orders;
import top.caodong0225.mq.service.MessageService;
import top.caodong0225.mq.store.InMemoryMessageStore;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private InMemoryMessageStore messageStore;

    // 显示下单页面
    @GetMapping("/new")
    public String showOrderForm(Model model) {
        model.addAttribute("order", new Orders());
        return "order_form";
    }

    // 提交订单
    @PostMapping("/submit")
    public String submitOrder(@RequestParam("goodsName") String goodsName,
                              @RequestParam("goodsPrice") String goodsPrice,
                              @RequestParam("goodsCount") String goodsCount,
                              @RequestParam(value = "queue", defaultValue = "queueGoods") String queue,
                              Model model) {
        Orders order = new Orders();
        order.setGoodsName(goodsName);
        order.setGoodsPrice(goodsPrice);
        order.setGoodsCount(goodsCount);
        String orderId = messageService.sendTo(queue, order);
        Orders sentOrder = messageStore.get(orderId);

        model.addAttribute("order", sentOrder);
        model.addAttribute("orderId", orderId);
        return "order_result";
    }

    @GetMapping("/list")
    public String showAllOrders(Model model) {
        model.addAttribute("orders", messageStore.getAll().values());
        return "order_list";
    }

    // 显示所有待处理订单（状态为 PENDING）
    @GetMapping("/pending")
    public String showPendingOrders(Model model) {
        model.addAttribute("orders", messageStore.getAll().values().stream()
                .filter(order -> "SENT".equals(order.getOrderStatus()))
                .toList());
        return "order_pending";
    }

    // 处理订单，将状态更新为 COMPLETED
    @PostMapping("/process/{orderId}")
    public String processOrder(@PathVariable String orderId) {
        Orders order = messageStore.get(orderId);
        if (order != null && "SENT".equals(order.getOrderStatus())) {
            order.setOrderStatus("COMPLETED");
            messageStore.update(orderId, order);
        }
        return "redirect:/order/pending";
    }

}