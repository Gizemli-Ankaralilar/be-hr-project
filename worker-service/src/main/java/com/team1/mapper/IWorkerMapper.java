package com.team1.mapper;

import com.team1.dto.response.WorkerListResponse;
import com.team1.rabbitmq.model.AuthWorkerModel;
import com.team1.repository.entity.Worker;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface IWorkerMapper {

    IWorkerMapper INSTANCE = Mappers.getMapper(IWorkerMapper.class);

    Worker toAuthWorker(AuthWorkerModel authWorkerModel);

    WorkerListResponse toWorkerList(Worker worker);

    WorkerListResponse toWorkerList(Optional<Worker> x);
}
