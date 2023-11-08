package com.team1.mapper;

import com.team1.rabbitmq.model.AuthWorkerModel;
import com.team1.repository.entity.Worker;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
<<<<<<< HEAD
    date = "2023-11-08T12:33:46+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8.1 (Amazon.com Inc.)"
=======
    date = "2023-11-08T21:18:39+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Amazon.com Inc.)"
>>>>>>> c6b5c687caefc555ff8ca77d26c5d2cae6b7d703
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
        worker.companyId( authWorkerModel.getCompanyId() );
        worker.username( authWorkerModel.getUsername() );
        worker.email( authWorkerModel.getEmail() );
        worker.firstName( authWorkerModel.getFirstName() );
        worker.lastName( authWorkerModel.getLastName() );
        worker.phone( authWorkerModel.getPhone() );
        worker.address( authWorkerModel.getAddress() );

        return worker.build();
    }
}
