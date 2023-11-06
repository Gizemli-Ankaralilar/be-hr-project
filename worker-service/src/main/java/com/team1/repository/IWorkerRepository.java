package com.team1.repository;

import com.team1.repository.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWorkerRepository extends JpaRepository<Worker, String> {
}
