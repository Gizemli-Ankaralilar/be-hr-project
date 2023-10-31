package com.team1.config.rabbitmq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitMqConfig {

    //company işlemleri
    private String createPostQueue = "register-queue";

    @Bean
    Queue createPostQueue(){
        return new Queue(createPostQueue);
    }
}
