package com.team1.rabbitmq.consumer;


import com.team1.rabbitmq.model.MailActivateModel;
import com.team1.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailActivateConsumer {
    private final MailSenderService mailSenderService;

    @RabbitListener(queues = "mail-activate-queue")
    public void sendActivateUserInfo(MailActivateModel mailActivateModel){
        mailSenderService.sendActivateUserInfo(mailActivateModel);
    }


}
