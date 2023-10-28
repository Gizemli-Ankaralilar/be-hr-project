package com.team1.rabbitmq.consumer;

import com.team1.rabbitmq.model.MailRegisterModel;
import com.team1.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailRegisterConsumer {

    private final MailService mailSenderService;

    @RabbitListener(queues = "mail-register-queue")
    public void sendRegisterUsersInfo(MailRegisterModel mailRegisterModel){
        mailSenderService.sendRegisterUsersInfo(mailRegisterModel);
    }

}