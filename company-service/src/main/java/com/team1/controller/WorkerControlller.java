package com.team1.controller;

//import com.team1.dto.request.UpdateCompanyRequestDto;
//import com.team1.dto.request.UpdateWorkerRequestDto;
import com.team1.dto.request.WorkerDto;
import com.team1.repository.entity.Worker;
import com.team1.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.team1.constant.EndPoints.*;
@RestController
@RequestMapping(WORKER)
@RequiredArgsConstructor
public class WorkerControlller {



    private final WorkerService workerService;

    @GetMapping
    public String hello() {
        return "Company Service";
    }



    @PostMapping("/worker-create")
    public ResponseEntity<Worker> createWorker(@RequestParam String token, @RequestBody WorkerDto dto){
        return ResponseEntity.ok(workerService.createWorkerUser(token, dto));
    }



//
//    @GetMapping(FINDALL_WORKER)
//    public ResponseEntity<List<Worker>> findAllWorker(){
//        return ResponseEntity.ok(workerService.findAllWorker());
//    }
//
////    @PutMapping(UPDATE_WORKER)
////    @CrossOrigin("*")
////    public ResponseEntity<Worker> updateWorker(@PathVariable String id, @RequestBody UpdateWorkerRequestDto dto){
////        Worker worker = workerService.updateWorker(id, dto);
////        return new ResponseEntity<>(worker, HttpStatus.OK);
////    }
//
//    @DeleteMapping(DELETE_WORKER)
//    public ResponseEntity<Boolean> deleteWorker(@RequestBody @PathVariable String id){
//        return ResponseEntity.ok(workerService.deleteWorker(id));
//    }
}
