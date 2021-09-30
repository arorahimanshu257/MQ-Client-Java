package com.rabbitmq.practice.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.practice.dto.OrderStatus;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OrderConsumer {

    @RabbitListener(queues = "#{queue}")
    public void consumedMessageFromQueue(Message message) throws IOException {

        String url = new ObjectMapper().readValue(message.getBody(), String.class);

        //OrderStatus order = new ObjectMapper().readValue(message.getBody(),OrderStatus.class);

        System.out.println("message received : " + url);
    }
}
