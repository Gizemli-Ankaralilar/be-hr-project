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

    @Bean
    public Queue mailQueue(){
        return new Queue(mailQueueName);
    }

    @Bean
    public Binding bindingMail(final Queue mailQueue, final DirectExchange exchange) {
        return BindingBuilder.bind(mailQueue).to(exchange).with(mailBindingKey);
    }
}
