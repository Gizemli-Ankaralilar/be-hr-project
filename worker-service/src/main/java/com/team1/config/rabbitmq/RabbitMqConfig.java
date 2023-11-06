package com.team1.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    //USERDAN WORKER
    private String queueUserWorker = "queueUserWorker";
    @Bean
    Queue queueUserWorker(){
        return new Queue(queueUserWorker);
    }

}
