package com.team1.repository;

import com.team1.repository.entity.Finance;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IFinanceRepository extends MongoRepository<Finance, String> {
}
