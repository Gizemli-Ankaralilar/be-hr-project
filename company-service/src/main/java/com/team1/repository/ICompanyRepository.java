package com.team1.repository;

import com.team1.repository.entity.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICompanyRepository extends MongoRepository<Company, String> {
}
