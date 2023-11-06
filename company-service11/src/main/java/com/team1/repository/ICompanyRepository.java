package com.team1.repository;

import com.team1.repository.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICompanyRepository extends JpaRepository<Company, String> {
    Boolean existsByUsername(String username);

}
