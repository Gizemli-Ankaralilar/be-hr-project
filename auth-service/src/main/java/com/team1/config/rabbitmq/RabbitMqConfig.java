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

    @Value("${rabbitmq.auth-exchange}")
    private String exchange;
    @Value("${rabbitmq.mail-queue}")
    private String mailQueueName;
    @Value("${rabbitmq.mail-binding-key}")
    private String mailBindingKey;

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchange);
    }

    private String mailRegisterQueue = "mail-register-queue";
    private String mailRegisterBinding = "mail-register-binding";

    @Bean
    Queue mailRegisterQueue(){
        return new Queue(mailRegisterQueue);
    }
    @Bean
    public Binding mailRegisterBinding(final Queue mailRegisterQueue, final DirectExchange authExchange){
        return BindingBuilder.bind(mailRegisterQueue).to(authExchange).with(mailRegisterBinding);
    }

    //Mail activate sender producer
    private String mailActivateQueue = "mail-activate-queue";
    private String mailActivateBinding = "mail-activate-binding";

    @Bean
    Queue mailActivateQueue(){
        return new Queue(mailActivateQueue);
    }

    @Bean
    public Binding mailActivateBinding(final Queue mailActivateQueue, final DirectExchange authExchange){
        return BindingBuilder.bind(mailActivateQueue).to(authExchange).with(mailActivateBinding);
    }
}
