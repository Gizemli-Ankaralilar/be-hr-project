package com.team1.rabbitmq.producer;

import com.team1.rabbitmq.model.AuthCompanyModel;
import com.team1.rabbitmq.model.AuthWorkerModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthWorkerProducer {
    private String exchangeWorker = "exchangeWorker";
    private String createWorkerBindingKey = "createWorkerBindingKey";

    private final RabbitTemplate rabbitTemplate;
    public void authWorker(AuthWorkerModel model){
        rabbitTemplate.convertAndSend(exchangeWorker,createWorkerBindingKey,model);
    }
}
