package com.team1.rabbitmq.producer;

import com.team1.rabbitmq.model.CreateAuthModel;
import com.team1.rabbitmq.model.QueryAuthIdModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueryAuthIdProducer {
    @Value("${rabbitmqKey.auth-exchange}")
    private String exchange = "auth-exchange";
    @Value("${rabbitmqKey.register-queue}")
    private String createAuthBindingKey = "auth-company-bindingkey";



    private final RabbitTemplate rabbitTemplate;//Rabbitteki bütün işlemleri bu arkadaş yapıyor

    //company den direk uzur kaydı oluşturmak için
    public Object queryAuthId(QueryAuthIdModel model){
        return rabbitTemplate.convertSendAndReceive(exchange,createAuthBindingKey, model);
    }
}
