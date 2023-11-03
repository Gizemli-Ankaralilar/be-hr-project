package com.team1.repository;

import com.team1.repository.entity.Worker;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IWorkerRepository extends MongoRepository<Worker, String> {
}
