package com.team1.repository;


import com.team1.repository.entity.Finance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IFinanceRepository extends JpaRepository<Finance, Long> {
//    @Query("SELECT c.id FROM Company c where c.authId = :authId")
//    Optional<Long> companyById(@Param("authId") Long authId);

    @Query("SELECT sum(f.incomeAmount) FROM Finance f where f.companyId = :companyId")
    Optional<Double> profit(@Param("companyId") Long companyId);

    @Query("SELECT sum(f.spendingAmount) FROM Finance f where f.companyId = :companyId")
    Optional<Double> loss(@Param("companyId") Long companyId);


}
