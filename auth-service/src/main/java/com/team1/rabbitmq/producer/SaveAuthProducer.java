package com.team1.rabbitmq.producer;

import com.team1.rabbitmq.model.SaveAuthModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveAuthProducer {

    String directExchangeAuth = "direct-exchange-auth";
    String saveBindingKey = "save-binding-key";

    public final RabbitTemplate rabbitTemplate;

    public void convertAndSendUser(SaveAuthModel model){
        rabbitTemplate.convertAndSend(directExchangeAuth,saveBindingKey, model);
    }
}
