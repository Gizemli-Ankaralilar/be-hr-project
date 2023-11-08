package com.team1.rabbitmq.producer;

import com.team1.rabbitmq.model.CompanyWorkerTokenModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyWorkerTokenProducer {
    private String exchangeCompanyToken = "exchangeCompanyToken";
    private String createCompanyTokenBindingKey = "createCompanyTokenBindingKey";

    private final RabbitTemplate rabbitTemplate;

    public void workerListener(CompanyWorkerTokenModel model){
        rabbitTemplate.convertAndSend(exchangeCompanyToken,createCompanyTokenBindingKey,model);
    }
}
