package com.team1.rabbitmq.producer;

import com.team1.rabbitmq.model.AuthUserModel;
import com.team1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserConsumer {
//    private final UserProfileService userProfileService;
//
//    @RabbitListener(queues = ("${rabbitmq.queueRegister}"))
//    public void createUser(RegisterModel model){
//        log.info("User {}", model.toString());
//        userProfileService.createUser(model);
//    }
    private final UserService userService;
    @RabbitListener(queues = "queueUser")
    public void createUser(AuthUserModel model){
        userService.createUser(model);
    }
}
