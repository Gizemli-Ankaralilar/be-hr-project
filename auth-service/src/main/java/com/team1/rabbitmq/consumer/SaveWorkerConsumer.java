package com.team1.rabbitmq.consumer;


import com.team1.rabbitmq.model.SaveWorkerModel;
import com.team1.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SaveWorkerConsumer {
    private final AuthService authService;

    @RabbitListener(queues = "queue-worker")
    public void createFromQueue(SaveWorkerModel model){
        authService.saveCompanyRabbit(model);
    }

}
