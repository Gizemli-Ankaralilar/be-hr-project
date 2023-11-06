package com.team1.rabbitmq.consumer;

import com.team1.rabbitmq.model.AuthUserModel;
import com.team1.rabbitmq.model.CompanyWorkerAuthModel;
import com.team1.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyWorkerAuthConsumer {

    private final AuthService authService;
    @RabbitListener(queues = "queueAuth")
    public void createUser(CompanyWorkerAuthModel model){
        authService.createWorkerAuth(model);
    }

}
