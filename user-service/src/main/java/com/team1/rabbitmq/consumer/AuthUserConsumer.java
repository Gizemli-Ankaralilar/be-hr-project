package com.team1.rabbitmq.consumer;

import com.team1.rabbitmq.model.AuthUserModel;
import com.team1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserConsumer {
    private final UserService userService;

    @RabbitListener(queues = "queueUser")
    public void createUser(AuthUserModel model){
        userService.createAuthUser(model);
    }
}
