package com.team1.rabbitmq.consumer;

import com.team1.rabbitmq.model.CreateAuthModel;
import com.team1.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAuthConsumer {
    private final AuthService authService;
    @RabbitListener(queues = "register-queue")
    public void createSaveAuth(CreateAuthModel model){
        authService.createAuthRabbit(model);
    }
}
