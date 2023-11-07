package com.team1.rabbitmq.consumer;

import com.team1.rabbitmq.model.AuthWorkerModel;
import com.team1.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthWorkerConsumer {
    WorkerService workerService;
    @RabbitListener(queues = "queueAuth")
    public void createUser(AuthWorkerModel model){
        workerService.createAuthWorker(model);
    }
}
