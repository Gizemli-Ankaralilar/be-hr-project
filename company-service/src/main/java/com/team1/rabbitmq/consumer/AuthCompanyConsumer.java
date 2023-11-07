package com.team1.rabbitmq.consumer;

import com.team1.rabbitmq.model.AuthCompanyModel;
import com.team1.rabbitmq.model.CompanyWorkerAuthModel;
import com.team1.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthCompanyConsumer {

    private final CompanyService companyService;
    @RabbitListener(queues = "queueCompany")
    public void createCompany(AuthCompanyModel model){
        companyService.createAuthCompany(model);
    }
}
