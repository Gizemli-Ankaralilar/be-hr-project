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

    String directExchangeCompany = "direct-exchange-auth";
    String queueCompany = "queue-company";
    String saveBindingKeyCompany = "save-binding-key-company";
    @Bean
    DirectExchange directExchangeCompany(){
        return new DirectExchange(directExchangeCompany);
    }
    @Bean
    Queue queueCompany(){
        return new Queue(queueCompany);
    }
    @Bean
    public Binding saveBindingDirectExchangeCompany(final Queue queueCompany,  final DirectExchange directExchangeCompany){
        return BindingBuilder.bind(queueCompany).to(directExchangeCompany).with(saveBindingKeyCompany);
    }

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
