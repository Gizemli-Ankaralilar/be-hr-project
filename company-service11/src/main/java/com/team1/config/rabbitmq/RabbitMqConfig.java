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

    //COMPANYDEN WORKER A GİDİYOR

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
//COMPANYDEN USERA
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

}
