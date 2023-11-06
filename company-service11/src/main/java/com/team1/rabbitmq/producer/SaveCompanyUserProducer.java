package com.team1.rabbitmq.producer;

import com.team1.rabbitmq.model.SaveCompanyUserModel;
import com.team1.rabbitmq.model.SaveWorkerModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveCompanyUserProducer {
    String directExchangeUser = "direct-exchange-user";
    String saveBindingKeyUser = "save-binding-key-user";

    public final RabbitTemplate rabbitTemplate;

    public void convertAndSendUser(SaveCompanyUserModel model){
        rabbitTemplate.convertAndSend(directExchangeUser,saveBindingKeyUser, model);
    }
}
