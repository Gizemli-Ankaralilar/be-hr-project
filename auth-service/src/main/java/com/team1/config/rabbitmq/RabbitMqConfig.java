package com.team1.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitMqConfig {
    //Aminenin yazdıkları
    @Value("${rabbitmqKey.auth-exchange}")
    private String exchange;

    //mail icin
    @Value("${rabbitmqKey.register-binding}")
    private String registerBindingKey;
    @Value("${rabbitmqKey.register-queue}")
    private String registerQueueName;

    @Value("${rabbitmqKey.mail-queue}")
    private String  mailQueueName;
    @Value("${rabbitmqKey.mail-binding-key}")
    private String  mailBindingKey;
    // mail işlemleri
    @Bean
    public Queue mailQueue(){
        return new Queue(mailQueueName);
    }

    @Bean
    public Binding bindingMail(final Queue mailQueue, final DirectExchange exchange) {
        return BindingBuilder.bind(mailQueue).to(exchange).with(mailBindingKey);
    }

    //AUTHDAN USER A
    private String exchangeUser = "exchangeUser";
    private String queueUser = "queueUser";
    private String createUserBindingKey = "createUserBindingKey";

    @Bean
    Queue queueUser(){
        return new Queue(queueUser);
    }
    @Bean
    DirectExchange exchangeUser(){
        return new DirectExchange(exchangeUser);
    }
    @Bean
    public Binding postBindingKey(final Queue queueUser, final DirectExchange exchangeUser){
        return BindingBuilder.bind(queueUser).to(exchangeUser).with(createUserBindingKey);
    }
}


