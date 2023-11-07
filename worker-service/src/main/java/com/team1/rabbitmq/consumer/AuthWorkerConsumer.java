package com.team1.rabbitmq.consumer;

import com.team1.rabbitmq.model.AuthWorkerModel;
import com.team1.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthWorkerConsumer {
    private final WorkerService workerService;
    @RabbitListener(queues = "queueWorker")
    public void createUser(AuthWorkerModel model){
        workerService.createAuthWorker(model);
    }
}
