package com.team1.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitMqConfig {
//    //Aminenin yazdıkları
//    @Value("${rabbitmqKey.auth-exchange}")
//    private String exchange;
//
//    //mail icin
//    @Value("${rabbitmqKey.register-binding}")
//    private String registerBindingKey;
//    @Value("${rabbitmqKey.register-queue}")
//    private String registerQueueName;
//
//    @Value("${rabbitmqKey.mail-queue}")
//    private String  mailQueueName;
//    @Value("${rabbitmqKey.mail-binding-key}")
//    private String  mailBindingKey;
//    // mail işlemleri
//    @Bean
//    public Queue mailQueue(){
//        return new Queue(mailQueueName);
//    }
//
////    @Bean
////    public Binding bindingMail(final Queue mailQueue, final DirectExchange exchange) {
////        return BindingBuilder.bind(mailQueue).to(exchange).with(mailBindingKey);
////    }

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
    public Binding userBindingKey(final Queue queueUser, final DirectExchange exchangeUser){
        return BindingBuilder.bind(queueUser).to(exchangeUser).with(createUserBindingKey);
    }

    //COMPANY DEN AUTHA
    private String queueAuth = "queueAuth";
    @Bean
    Queue queueAuth(){
        return new Queue(queueAuth);
    }

    //AUTHDAN COMPANY E
    private String exchangeCompany = "exchangeCompany";
    private String queueCompany = "queueCompany";
    private String createCompanyBindingKey = "createCompanyBindingKey";

    @Bean
    Queue queueCompany(){
        return new Queue(queueCompany);
    }
    @Bean
    DirectExchange exchangeCompany(){
        return new DirectExchange(exchangeCompany);
    }
    @Bean
    public Binding companyBindingKey(final Queue queueCompany, final DirectExchange exchangeCompany){
        return BindingBuilder.bind(queueCompany).to(exchangeCompany).with(createCompanyBindingKey);
    }

    //AUTHDAN WORKERA
    private String exchangeWorker = "exchangeWorker";
    private String queueWorker = "queueWorker";
    private String createWorkerBindingKey = "createWorkerBindingKey";
    @Bean
    Queue queueWorker(){
        return new Queue(queueWorker);
    }
    @Bean
    DirectExchange exchangeWorker(){
        return new DirectExchange(exchangeWorker);
    }
    @Bean
    public Binding workerBindingKey(final Queue queueWorker, final DirectExchange exchangeWorker){
        return BindingBuilder.bind(queueWorker).to(exchangeWorker).with(createWorkerBindingKey);
    }

    //AUTHDAN MAİLE
    private String exchangeMail = "exchangeMail";
    private String queueMail = "queueMail";
    private String createMailBindingKey = "createMailBindingKey";
    @Bean
    Queue queueMail(){
        return new Queue(queueMail);
    }
    @Bean
    DirectExchange exchangeMail(){
        return new DirectExchange(exchangeMail);
    }
    @Bean
    public Binding mailBindingKey(final Queue queueMail, final DirectExchange exchangeMail){
        return BindingBuilder.bind(queueMail).to(exchangeMail).with(createMailBindingKey);
    }

}


