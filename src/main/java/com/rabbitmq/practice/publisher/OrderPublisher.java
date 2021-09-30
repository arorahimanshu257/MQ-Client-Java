package com.rabbitmq.practice.publisher;

import com.rabbitmq.practice.dto.Order;
import com.rabbitmq.practice.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;
//    @Value("${exchange}")
//    private String topicExchange;
//    @Value("${bindingKey}")
//    private String bindingKey;

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName) {
        order.setOrderId(UUID.randomUUID().toString());
        OrderStatus status =
                new OrderStatus(order, "process", "order successfully placed in: " + restaurantName);

        Map send = new HashMap<String, String>();
        send.put("sourceTemplate", "https://github.com/arorahimanshu257/Todo-React");
        send.put("owner", "OctopusScope");
        send.put("repoName", "reactRepo");
        send.put("replyTo", "order_route");

        rabbitTemplate.convertAndSend("order_exchange", "asset_github_#", send);
        System.out.println("status : -----  " + send);
        return "success";
    }
}
