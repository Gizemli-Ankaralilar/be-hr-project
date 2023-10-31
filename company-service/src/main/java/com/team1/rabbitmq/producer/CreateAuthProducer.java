package com.team1.rabbitmq.producer;

import com.team1.rabbitmq.model.CreateAuthModel;
import com.team1.rabbitmq.model.CreateCompanyAuthModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Service
@RequiredArgsConstructor
public class CreateAuthProducer {

    @Value("${rabbitmqKey.auth-exchange}")
    private String exchange = "auth-exchange";
    @Value("${rabbitmqKey.register-queue}")
    private String createAuthBindingKey = "auth-company-bindingkey";



    private final RabbitTemplate rabbitTemplate;//Rabbitteki bütün işlemleri bu arkadaş yapıyor

    //company den direk uzur kaydı oluşturmak için
    public void createAuth(CreateAuthModel model){
        rabbitTemplate.convertAndSend(exchange,createAuthBindingKey, model);
    }
}
