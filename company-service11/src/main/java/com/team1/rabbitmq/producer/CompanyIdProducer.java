package com.team1.rabbitmq.producer;

import com.team1.rabbitmq.model.CompanyIdModel;
import com.team1.rabbitmq.model.SaveCompanyUserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyIdProducer {

    String directExchangeUserID = "direct-exchange-user-id";
    String saveBindingKeyUserID = "save-binding-key-user-id";

    RabbitTemplate rabbitTemplate;

    public Object convertSendAndReceiveId(CompanyIdModel model){
        return rabbitTemplate.convertSendAndReceive(directExchangeUserID,saveBindingKeyUserID, model);
    }
}
