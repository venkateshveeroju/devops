package com.devops.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMQConfig {

    // Declare a queue
    @Bean
    public Queue myQueue() {
        return new Queue("myQueue", false);  // 'false' indicates it's not durable
    }

    // Declare an exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("myExchange");
    }

    // Declare a binding between the queue and exchange
    @Bean
    public Binding binding(Queue myQueue, TopicExchange exchange) {
        return BindingBuilder.bind(myQueue).to(exchange).with("my.routing.key");
    }
}