package com.team1.rabbitmq.producer;

import com.team1.rabbitmq.model.AuthCompanyModel;
import com.team1.rabbitmq.model.AuthUserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthCompanyProducer {
    private String exchangeCompany = "exchangeCompany";
    private String createCompanyBindingKey = "createCompanyBindingKey";

    private final RabbitTemplate rabbitTemplate;
    public void authCompany(AuthCompanyModel model){
        rabbitTemplate.convertAndSend(exchangeCompany,createCompanyBindingKey,model);
    }
}
