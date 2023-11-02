package com.team1.repository;

import com.team1.repository.entity.Permit;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IPermitRepository extends MongoRepository<Permit, String> {
}
