package com.team1.rabbitmq.producer;

import com.team1.rabbitmq.model.SaveWorkerModel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkerSaveProducer {

    String directExchangeWorker = "direct-exchange-worker";
    String saveBindingKeyWorker = "save-binding-key-worker";
    public final RabbitTemplate rabbitTemplate;

    public void convertAndSendWorker(SaveWorkerModel model){
        rabbitTemplate.convertAndSend(directExchangeWorker,saveBindingKeyWorker, model);
    }

}
