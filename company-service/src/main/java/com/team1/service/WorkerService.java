package com.team1.service;

//import com.team1.dto.request.UpdateCompanyRequestDto;
//import com.team1.dto.request.UpdateWorkerRequestDto;
import com.team1.dto.request.WorkerDto;
import com.team1.exception.CompanyException;
import com.team1.exception.ErrorType;
import com.team1.rabbitmq.producer.CreateWorkerAuthProduces;
import com.team1.rabbitmq.producer.QueryAuthIdProducer;
import com.team1.repository.IWorkerRepository;
import com.team1.repository.entity.Company;
import com.team1.repository.entity.Worker;
import com.team1.utility.JwtTokenManager;
import com.team1.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService extends ServiceManager<Worker, String>{

    private final JwtTokenManager jwtTokenManager;
    private final IWorkerRepository workerRepository;
    private final CreateWorkerAuthProduces createWorkerAuthProduces;
    private final QueryAuthIdProducer queryAuthIdProducer;
    private Company company;

    public WorkerService(IWorkerRepository workerRepository, JwtTokenManager jwtTokenManager,
                         CreateWorkerAuthProduces createWorkerAuthProduces, QueryAuthIdProducer queryAuthIdProducer) {
        super(workerRepository);
        this.jwtTokenManager = jwtTokenManager;
        this.workerRepository = workerRepository;
        this.createWorkerAuthProduces = createWorkerAuthProduces;
        this.queryAuthIdProducer = queryAuthIdProducer;
    }



    public Worker createWorkerUser(String token, WorkerDto dto){
        String companyId = jwtTokenManager.getCompanyIdFromToken(token).orElseThrow(() -> {
            throw new CompanyException(ErrorType.INVALID_TOKEN);
        });
        Long authId = jwtTokenManager.getIdFromToken(token).orElseThrow(() -> {
            throw new CompanyException(ErrorType.INVALID_TOKEN);
        });
        Worker worker = Worker.builder().authId(authId).companyId(companyId).username(dto.getUsername())
                .password(dto.getPassword()).email(dto.getEmail()).build();
        //auth tablosuna kayıt eklenmedi!!!!
        return save(worker);
    }

//
//    @Transactional(readOnly = true)
//    public List<Worker> findAllWorker() {
//        return workerRepository.findAll();
//    }
//
////    public Worker updateWorker(String id, UpdateWorkerRequestDto dto){
////        Worker worker = workerRepository.findById(id)
////                .orElseThrow(() -> new WorkerException(ErrorType.WORKER_NOT_FOUND));
////        worker.setFirstName(dto.getFirstName());
////        worker.setLastName(dto.getLastName());
////        worker.setPassword(dto.getPassword());
////        worker.setPhone(dto.getPhone());
////        worker.setAddress(dto.getAddress());
////        return workerRepository.save(worker);
////    }
//
//    @Transactional
//    public Boolean deleteWorker(String id){
//        Optional<Worker> worker = workerRepository.findById(id);
//        if (worker.isEmpty()){
//            throw new CompanyException(ErrorType.WORKER_NOT_FOUND);
//        }
//        workerRepository.delete(worker.get());
//        return true;
//    }
}
