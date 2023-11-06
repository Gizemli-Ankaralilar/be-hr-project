package com.team1.rabbitmq.consumer;

import com.team1.rabbitmq.model.SaveCompanyModel;
import com.team1.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveCompanyConsumer {
    private final CompanyService companyService;

    @RabbitListener(queues = "queue-company")
    public void createFromQueue(SaveCompanyModel model){
        companyService.saveCompanyRabbit(model);
    }
}
