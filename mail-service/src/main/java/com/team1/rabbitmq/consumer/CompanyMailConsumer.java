package com.team1.rabbitmq.consumer;

import com.team1.rabbitmq.model.AuthMailModel;
import com.team1.rabbitmq.model.CompanyMailModel;
import com.team1.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyMailConsumer {

    private final MailService mailService;

    @RabbitListener(queues = "queueCompanyMail")
    public void createCompanyMail(CompanyMailModel model){
        mailService.createCompanyMail(model);
    }
}
