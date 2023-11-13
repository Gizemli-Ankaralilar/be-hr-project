package com.team1.manager;

import com.team1.repository.entity.Worker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "auth-company-manager", url = "http://localhost:9094/api/v1/worker", decode404 = true)
public interface ICompanyWorkerManager {

    @GetMapping("/finAll")
    ResponseEntity<List<Worker>> findAllWorker();
}
