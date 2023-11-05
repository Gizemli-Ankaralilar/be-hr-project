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
//autdan usera
    String directExchangeAuth = "direct-exchange-auth";

    String queueAuth = "queue-auth";

    String saveBindingKey = "save-binding-key";

    @Bean
    DirectExchange directExchangeAuth(){
        return new DirectExchange(directExchangeAuth);
    }

    @Bean
    Queue queueAuth(){
        return new Queue(queueAuth);
    }

    @Bean
    public Binding saveBindingDirectExchange(final Queue queueAuth,  final DirectExchange directExchangeAuth){
        return BindingBuilder.bind(queueAuth).to(directExchangeAuth).with(saveBindingKey);
    }
    ///COMPANYDEN USERA
    String directExchangeUser = "direct-exchange-user";
    String queueUser = "queue-user";
    String saveBindingKeyUser = "save-binding-key-user";
    @Bean
    DirectExchange directExchangeUser(){
        return new DirectExchange(directExchangeUser);
    }
    @Bean
    Queue queueUser(){
        return new Queue(queueUser);
    }
    @Bean
    public Binding saveBindingDirectExchangeUser(final Queue queueUser,  final DirectExchange directExchangeUser){
        return BindingBuilder.bind(queueUser).to(directExchangeUser).with(saveBindingKeyUser);
    }

    //COMPANYDEN USERA COMPANYID SORGULAMA KUYRUĞU

    String directExchangeUserID = "direct-exchange-user-id";
    String queueUserID = "queue-user-id";
    String saveBindingKeyUserID = "save-binding-key-user-id";
    @Bean
    DirectExchange directExchangeUserID(){
        return new DirectExchange(directExchangeUserID);
    }
    @Bean
    Queue queueUserID(){
        return new Queue(queueUserID);
    }
    @Bean
    public Binding saveBindingDirectExchangeUserID(final Queue queueUserID,  final DirectExchange directExchangeUserID){
        return BindingBuilder.bind(queueUserID).to(directExchangeUserID).with(saveBindingKeyUserID);
    }

    //USERDAN WORKERA KAYIT KUYRUĞU

    String directExchangeUserWorker = "direct-exchange-user-worker";
    String queueUserWorker = "queue-user-worker";
    String saveBindingKeyUserWorker = "save-binding-key-user-worker";
    @Bean
    DirectExchange directExchangeUserWorker(){
        return new DirectExchange(directExchangeUserWorker);
    }
    @Bean
    Queue queueUserWorker(){
        return new Queue(queueUserWorker);
    }
    @Bean
    public Binding saveBindingDirectExchangeUserWorker(final Queue queueUserWorker,  final DirectExchange directExchangeUserWorker){
        return BindingBuilder.bind(queueUserWorker).to(directExchangeUserWorker).with(saveBindingKeyUserWorker);
    }

}
