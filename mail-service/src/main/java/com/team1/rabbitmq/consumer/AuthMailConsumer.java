package com.team1.rabbitmq.consumer;

import com.team1.rabbitmq.model.AuthMailModel;
import com.team1.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthMailConsumer {

    private final MailService mailService;

    @RabbitListener(queues = "queueMail")
    public void createUser(AuthMailModel model){
        mailService.createAuthMail(model);
    }

}
