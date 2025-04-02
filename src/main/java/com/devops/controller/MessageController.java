package com.devops.controller;


import com.devops.rabbitmq.MessageProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private MessageProducerService messageProducerService;

    @GetMapping("/sendMessage")
    public String sendMessage() {
        messageProducerService.sendMessage("Hello, RabbitMQ!");
        return "Message Sent!";
    }
}