package com.team1.rabbitmq.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCompanyAuthProduces {
    @Value("${rabbitmqKey.auth-exchange}")
    private String exchange = "auth-exchange";
    @Value("${rabbitmqKey.register-queue}")
    private String createAuthBindingKey = "auth-company-bindingkey";


    private final RabbitTemplate rabbitTemplate;//Rabbitteki bütün işlemleri bu arkadaş yapıyor

    //auth'a auth bilgileri ile birlikte commandId eklemek içi
    public void createCompanyAuth(com.team1.rabbitmq.model.CreateCompanyAuthModel model){
        rabbitTemplate.convertAndSend(exchange,createAuthBindingKey, model);
    }

}
