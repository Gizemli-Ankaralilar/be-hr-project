package com.team1.rabbitmq.consumer;

import com.team1.rabbitmq.model.AuthWorkerModel;
import com.team1.rabbitmq.model.CompanyWorkerTokenModel;
import com.team1.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyWorkerTokenConsumer {
    private final WorkerService workerService;

    @RabbitListener(queues = "queueCompanyToken")
    public void workerListener(CompanyWorkerTokenModel model){
        workerService.workerListener(model);
    }
}
