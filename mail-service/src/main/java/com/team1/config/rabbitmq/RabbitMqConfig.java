package com.team1.config.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    //AUTHDAN MAİLE KUYRUK
    private String queueMail = "queueMail";
    @Bean
    Queue queueMail(){
        return new Queue(queueMail);
    }
}
