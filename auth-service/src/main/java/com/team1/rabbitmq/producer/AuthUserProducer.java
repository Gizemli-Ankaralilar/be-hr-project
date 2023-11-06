package com.team1.rabbitmq.producer;

import com.team1.rabbitmq.model.AuthUserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserProducer {

    private String exchangeUser = "exchangeUser";
    private String createUserBindingKey = "createUserBindingKey";

    private final RabbitTemplate rabbitTemplate;
    public void createUser(AuthUserModel model){
        rabbitTemplate.convertAndSend(exchangeUser,createUserBindingKey,model);
    }
}

