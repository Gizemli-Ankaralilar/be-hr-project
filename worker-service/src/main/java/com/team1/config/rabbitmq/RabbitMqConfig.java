package com.team1.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    String directExchangeWorker = "direct-exchange-worker";
    String queueWorker = "queue-worker";
    String saveBindingKeyWorker = "save-binding-key-worker";
    @Bean
    DirectExchange directExchangeWorker(){
        return new DirectExchange(directExchangeWorker);
    }
    @Bean
    Queue queueWorker(){
        return new Queue(queueWorker);
    }
    @Bean
    public Binding saveBindingDirectExchangeWorker(final Queue queueWorker,  final DirectExchange directExchangeWorker){
        return BindingBuilder.bind(queueWorker).to(directExchangeWorker).with(saveBindingKeyWorker);
    }
}
