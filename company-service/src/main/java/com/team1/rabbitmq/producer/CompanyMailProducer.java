package com.team1.rabbitmq.producer;

import com.team1.rabbitmq.model.CompanyMailModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyMailProducer {
    private String exchangeCompanyMail = "exchangeCompanyMail";
    private String createCompanyMailBindingKey = "createCompanyMailBindingKey";
    private final RabbitTemplate rabbitTemplate;

    public void createCompanyMail(CompanyMailModel model){
        rabbitTemplate.convertAndSend(exchangeCompanyMail,createCompanyMailBindingKey,model);
    }
}
