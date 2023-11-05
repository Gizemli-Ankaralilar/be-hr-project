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

    //Companyden worker geliyor
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
