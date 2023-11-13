package com.team1.rabbitmq.consumer;

import com.team1.mapper.IWorkerMapper;
import com.team1.rabbitmq.model.AuthWorkerModel;
import com.team1.rabbitmq.model.CompanyWorkerTokenModel;
import com.team1.repository.entity.Worker;
import com.team1.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyWorkerTokenConsumer {
    private final WorkerService workerService;

//    @RabbitListener(queues = "queueCompanyToken")
//    public void workerListener(CompanyWorkerTokenModel model){
//        workerService.workerListener(model);
//    }

    public List<Object> workerList(CompanyWorkerTokenModel model){
        Optional<Worker> worker = workerService.findAllWorker12(model);
        return worker.stream()
                .map(x-> IWorkerMapper.INSTANCE.toWorkerList(x)).collect(Collectors.toList());
    }
}
