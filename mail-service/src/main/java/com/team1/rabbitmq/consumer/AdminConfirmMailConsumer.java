package com.team1.rabbitmq.consumer;

import com.team1.rabbitmq.model.AdminConfirmMailModel;
import com.team1.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminConfirmMailConsumer {
    private final MailService mailService;

    @RabbitListener(queues = "queueAdminMail")
    public void createAdminConfirmMail(AdminConfirmMailModel model){
        mailService.createAdminConfirmMail(model);
    }

}
