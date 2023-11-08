package com.team1.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    //AUTHDAN WORKER
    private String queueMail = "queueMail";
    @Bean
    Queue queueMail(){
        return new Queue(queueMail);
    }
    //COMPANYDEN WORKERA
    private String queueCompanyToken = "queueCompanyToken";
    @Bean
    Queue queueCompanyToken(){
        return new Queue(queueCompanyToken);
    }

}
