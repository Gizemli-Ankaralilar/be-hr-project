package com.team1.controller;

//import com.team1.dto.request.UpdateCompanyRequestDto;
//import com.team1.dto.request.UpdateWorkerRequestDto;
import com.team1.dto.request.SaveCompanyDto;
import com.team1.dto.request.SaveWorkerDto;
import com.team1.dto.request.WorkerDto;
import com.team1.repository.entity.Company;
import com.team1.repository.entity.Worker;
import com.team1.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.team1.constant.EndPoints.*;
@RestController
@RequestMapping(WORKER)
@RequiredArgsConstructor
public class WorkerControlller {


    private final WorkerService workerService;

    @PostMapping("/worker-create")
    public ResponseEntity<Boolean> createWorker(@RequestParam String token, @RequestBody WorkerDto dto){
        return ResponseEntity.ok(workerService.createWorkerUser(token, dto));
    }


    @GetMapping(FINDALL_WORKER)
    public ResponseEntity<List<Worker>> findAllWorker(){
        return ResponseEntity.ok(workerService.findAllWorker());
    }

    @DeleteMapping(DELETE_BY_ID)
    public ResponseEntity<Boolean> deletePost(@RequestParam String token, @RequestParam String workerId){
        return ResponseEntity.ok(workerService.deleteWorker(token, workerId));
    }

//    @PutMapping(UPDATE_WORKER)
//    @CrossOrigin("*")
//    public ResponseEntity<Worker> updateWorker(@PathVariable String id, @RequestBody UpdateWorkerRequestDto dto){
//        Worker worker = workerService.updateWorker(id, dto);
//        return new ResponseEntity<>(worker, HttpStatus.OK);
//    }


}
