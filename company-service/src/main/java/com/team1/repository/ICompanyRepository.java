package com.team1.repository;

import com.team1.repository.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ICompanyRepository extends JpaRepository<Company, Long> {
//    Boolean existsByUsername(String username);

    @Query("SELECT c.id FROM Company c where c.authId = :authId")
    Optional<Long> companyById(@Param("authId") Long authId);

}
