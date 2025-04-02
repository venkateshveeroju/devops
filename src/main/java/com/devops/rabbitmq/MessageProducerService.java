package com.devops.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class MessageProducerService {

    //@Autowired
    private AmqpTemplate amqpTemplate;

    private String exchange = "myExchange";
    private String routingKey = "my.routing.key";

    // Method to send a message
    public void sendMessage(String message) {
        amqpTemplate.convertAndSend(exchange, routingKey, message);
        System.out.println("Sent message: " + message);
    }
}