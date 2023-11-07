package com.team1.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    //AUTHDAN USER A GELEN BİLGİLER
    private String queueUser = "queue-user";
    @Bean
    Queue queueUser(){
        return new Queue(queueUser);
    }

//    //USERDAN WORKER A
//    private String exchangeUserWorker = "exchangeUserWorker";
//    private String queueUserWorker = "queueUserWorker";
//    private String createUserWorkerBindingKey = "createUserWorkerBindingKey";
//
//    @Bean
//    Queue queueUserWorker(){
//        return new Queue(queueUserWorker);
//    }
//    @Bean
//    DirectExchange exchangeUserWorker(){
//        return new DirectExchange(exchangeUserWorker);
//    }
//    @Bean
//    public Binding userWorkerBindingKey(final Queue queueUserWorker, final DirectExchange exchangeUserWorker){
//        return BindingBuilder.bind(queueUserWorker).to(exchangeUserWorker).with(createUserWorkerBindingKey);
//    }
}
