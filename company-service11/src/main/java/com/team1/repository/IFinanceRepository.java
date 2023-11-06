package com.team1.repository;

import com.team1.repository.entity.Finance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFinanceRepository extends JpaRepository<Finance, String> {
}
