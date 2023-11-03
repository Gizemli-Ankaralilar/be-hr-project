package com.team1.rabbitmq.producer;

import com.team1.rabbitmq.model.SaveAuthModel;
import com.team1.rabbitmq.model.SaveCompanyModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveCompanyProducer {

    String directExchangeCompany = "direct-exchange-auth";
    String saveBindingKeyCompany = "save-binding-key-company";

    public final RabbitTemplate rabbitTemplate;

    public void convertAndSendCompany(SaveCompanyModel model){
        rabbitTemplate.convertAndSend(directExchangeCompany,saveBindingKeyCompany, model);
    }
}
