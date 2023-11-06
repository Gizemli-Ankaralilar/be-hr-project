package com.team1.rabbitmq.producer;

import com.team1.rabbitmq.model.CompanyWorkerAuthModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyWorkerAuthProducer {
    private String exchangeAuth = "exchangeAuth";
    private String createAuthBindingKey = "createAuthBindingKey";

    private final RabbitTemplate rabbitTemplate;
    public void createWorkerAuth(CompanyWorkerAuthModel model){
        rabbitTemplate.convertAndSend(exchangeAuth,createAuthBindingKey,model);
    }
}
