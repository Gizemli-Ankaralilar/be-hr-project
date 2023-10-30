package com.team1.repository;

import com.team1.repository.entity.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ICompanyRepository extends MongoRepository<Company, String> {

//    Optional<Company> findByTaxNumber(String taxNumber);
//
//    boolean existsByTaxNumberOrName(String taxNumber,String name);
//
//    Optional<Company> findByTaxNumberAndName(String taxNumber,String name);
}
