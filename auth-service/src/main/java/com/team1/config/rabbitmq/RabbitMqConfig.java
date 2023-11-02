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

    //company işlemleri
    @Value("${rabbitmqKey.register-queue}")
    private String createPostQueue;

    @Bean
    Queue createPostQueue(){
        return new Queue(createPostQueue);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchange);
    }

    // Register işlemleri
    @Bean
    Queue registerQueue() {
        return new Queue(registerQueueName);
    }

    @Bean
    public Binding bindingRegister(final Queue registerQueue, final DirectExchange exchange) {
        return BindingBuilder.bind(registerQueue).to(exchange).with(registerBindingKey);
    }

    // mail işlemleri

    @Bean
    public Queue mailQueue(){
        return new Queue(mailQueueName);
    }

    @Bean
    public Binding bindingMail(final Queue mailQueue, final DirectExchange exchange) {
        return BindingBuilder.bind(mailQueue).to(exchange).with(mailBindingKey);
    }
}
