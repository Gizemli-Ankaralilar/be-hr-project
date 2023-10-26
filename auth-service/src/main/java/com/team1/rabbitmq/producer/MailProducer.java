package com.team1.rabbitmq.producer;

import com.team1.rabbitmq.model.MailModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailProducer {

    private final RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.auth-exchange}")
    private String exchange;
    @Value("${rabbitmq.mail-binding-key}")
    private String bindingKey;


    public  void sendMail(MailModel model){
        rabbitTemplate.convertAndSend(exchange,bindingKey,model);
    }

}