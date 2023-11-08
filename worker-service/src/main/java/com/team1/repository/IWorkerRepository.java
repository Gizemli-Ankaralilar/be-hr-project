package com.team1.repository;

import com.team1.repository.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IWorkerRepository extends JpaRepository<Worker, Long> {
    @Query("SELECT w.firstName, w.lastName, w.fieldOfWork, w.phone, w.address FROM Worker w where w.companyId = :companyId")
    List<String> workerInformation(@Param("companyId") Long companyId);

}
