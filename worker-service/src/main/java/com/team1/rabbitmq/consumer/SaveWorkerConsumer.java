package com.team1.rabbitmq.consumer;

import com.team1.rabbitmq.model.SaveWorkerModel;
import com.team1.service.WorkerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SaveWorkerConsumer {
    private final WorkerService workerService;

    @RabbitListener(queues = "queue-worker")
    public void createFromQueue(SaveWorkerModel model){
        workerService.saveCompanyRabbit(model);
    }

}
