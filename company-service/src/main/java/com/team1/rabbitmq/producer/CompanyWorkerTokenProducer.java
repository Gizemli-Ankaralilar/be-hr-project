package com.team1.rabbitmq.producer;

import com.team1.rabbitmq.model.CompanyWorkerTokenModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyWorkerTokenProducer {
    private String exchangeCompanyToken = "exchangeCompanyToken";
    private String createCompanyTokenBindingKey = "createCompanyTokenBindingKey";

    private final RabbitTemplate rabbitTemplate;

    //List<Object den kaynaklı sorun olabilir!!!!
    public List<Object> workerListener(CompanyWorkerTokenModel model){
        //rabbitTemplate.convertAndSend(exchangeCompanyToken,createCompanyTokenBindingKey,model);
        return (List<Object>) rabbitTemplate.convertSendAndReceive(exchangeCompanyToken,createCompanyTokenBindingKey,model);
    }
}
