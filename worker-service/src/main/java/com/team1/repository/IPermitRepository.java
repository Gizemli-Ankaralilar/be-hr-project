package com.team1.repository;


import com.team1.repository.entity.Permit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPermitRepository extends JpaRepository<Permit, String> {
}
