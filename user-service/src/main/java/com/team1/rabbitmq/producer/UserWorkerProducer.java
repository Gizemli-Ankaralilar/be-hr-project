package com.team1.rabbitmq.producer;

import com.team1.rabbitmq.model.UserWorkerModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserWorkerProducer {

    String directExchangeUserWorker = "direct-exchange-user-worker";
    String saveBindingKeyUserWorker = "save-binding-key-user-worker";
    RabbitTemplate rabbitTemplate;
    public void convertAndSendUserWorker(UserWorkerModel model){
        rabbitTemplate.convertAndSend(directExchangeUserWorker,saveBindingKeyUserWorker, model);
    }
}
