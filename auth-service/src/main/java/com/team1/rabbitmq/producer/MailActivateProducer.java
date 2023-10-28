package com.team1.rabbitmq.producer;

import com.team1.rabbitmq.model.MailActivateModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailActivateProducer {

    private String exchange = "auth-exchange";
    private String mailActivateBinding = "mail-activate-binding";

    private final RabbitTemplate rabbitTemplate;

    public void sendActivateMail(MailActivateModel mailActivateModel){
        rabbitTemplate.convertAndSend(exchange,mailActivateBinding, mailActivateModel);
    }

}
