package com.team1.rabbitmq.consumer;

import com.team1.rabbitmq.model.MailRegisterModel;
import com.team1.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailActivateConsumer {

    private final MailService mailSenderService;

    @RabbitListener(queues = "mail-register-queue")
    public void sendRegisterUsersInfo(MailRegisterModel mailRegisterModel){
        mailSenderService.sendRegisterUsersInfo(mailRegisterModel);
    }
}
