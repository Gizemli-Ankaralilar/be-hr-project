package com.team1.rabbitmq.consumer;

import com.team1.rabbitmq.model.SaveAuthModel;
import com.team1.rabbitmq.model.SaveCompanyUserModel;
import com.team1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveCompanyUserConsumer {
    private final UserService userProfileService;

    @RabbitListener(queues = "queue-user-worker")
    public void createFromQueue(SaveCompanyUserModel model){
        userProfileService.saveWorkerAuth(model);
    }
}
