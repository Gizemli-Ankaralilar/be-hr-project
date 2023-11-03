package com.team1.controller;


import com.team1.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import static com.team1.constant.EndPoints.*;

@RestController
@RequestMapping(WORKER)
@RequiredArgsConstructor
public class WorkerController {

    private final WorkerService workerService;

//    @PostMapping("/worker-create")
//    public ResponseEntity<Boolean> createWorker(@RequestParam String token, @RequestBody WorkerDto dto){
//        return ResponseEntity.ok(workerService.createWorkerUser(token, dto));
//    }
//
//
//    @GetMapping(FINDALL_WORKER)
//    public ResponseEntity<List<Worker>> findAllWorker(){
//        return ResponseEntity.ok(workerService.findAllWorker());
//    }
//
//    @DeleteMapping(DELETE_BY_ID_WORKER)
//    public ResponseEntity<Boolean> deletePost(String token, String workerId){
//        return ResponseEntity.ok(workerService.deleteWorker(token, workerId));
//    }

//    @PutMapping(UPDATE_WORKER)
//    @CrossOrigin("*")
//    public ResponseEntity<Worker> updateWorker(@PathVariable String id, @RequestBody UpdateWorkerRequestDto dto){
//        Worker worker = workerService.updateWorker(id, dto);
//        return new ResponseEntity<>(worker, HttpStatus.OK);
//    }

}
