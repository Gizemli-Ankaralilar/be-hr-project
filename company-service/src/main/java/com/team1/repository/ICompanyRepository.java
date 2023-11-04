package com.team1.repository;

import com.team1.repository.entity.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ICompanyRepository extends MongoRepository<Company, String> {
    Boolean existsByUsername(String username);

}
