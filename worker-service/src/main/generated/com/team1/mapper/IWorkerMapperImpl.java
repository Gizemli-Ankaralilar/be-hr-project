package com.team1.mapper;

import com.team1.rabbitmq.model.AuthWorkerModel;
import com.team1.repository.entity.Worker;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-07T13:06:25+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class IWorkerMapperImpl implements IWorkerMapper {

    @Override
    public Worker toAuthWorker(AuthWorkerModel authWorkerModel) {
        if ( authWorkerModel == null ) {
            return null;
        }

        Worker.WorkerBuilder<?, ?> worker = Worker.builder();

        worker.authId( authWorkerModel.getAuthId() );

        return worker.build();
    }
}
