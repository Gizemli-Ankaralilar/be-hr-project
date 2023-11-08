package com.team1.rabbitmq.producer;

import com.team1.rabbitmq.model.AdminConfirmMailModel;
import com.team1.rabbitmq.model.AuthMailModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminConfirmMailProducer {
    private String exchangeMail = "exchangeMail";
    private String createMailBindingKey = "createMailBindingKey";

    private final RabbitTemplate rabbitTemplate;
    public void sendActivationCode(AdminConfirmMailModel model){
        rabbitTemplate.convertAndSend(exchangeMail,createMailBindingKey, model);
    }

}
