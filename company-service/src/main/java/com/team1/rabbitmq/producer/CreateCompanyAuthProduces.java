package com.team1.rabbitmq.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCompanyAuthProduces {
    private String exchange = "company-exchange";
    private String createAuthBindingKey = "auth-bindingkey";



    private final RabbitTemplate rabbitTemplate;//Rabbitteki bütün işlemleri bu arkadaş yapıyor

    //auth'a auth bilgileri ile birlikte commandId eklemek içi
    public void createCompanyAuth(com.team1.rabbitmq.model.CreateCompanyAuthModel model){
        rabbitTemplate.convertAndSend(exchange,createAuthBindingKey, model);
    }

}
