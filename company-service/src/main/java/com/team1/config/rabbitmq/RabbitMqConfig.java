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
//COMPANY DEN AUTHA
    private String exchangeAuth = "exchangeAuth";
    private String queueAuth = "queueAuth";
    private String createAuthBindingKey = "createAuthBindingKey";

    @Bean
    Queue queueAuth(){
        return new Queue(queueAuth);
    }
    @Bean
    DirectExchange exchangeAuth(){
        return new DirectExchange(exchangeAuth);
    }
    @Bean
    public Binding postBindingKey(final Queue queueAuth, final DirectExchange exchangeAuth){
        return BindingBuilder.bind(queueAuth).to(exchangeAuth).with(createAuthBindingKey);
    }
    //AUTHDAN COMPANY'E
    private String queueCompany = "queueCompany";
    @Bean
    Queue queueCompany(){
        return new Queue(queueCompany);
    }
}
