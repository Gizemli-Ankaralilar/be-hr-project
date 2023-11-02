package com.team1.controller;

//import com.team1.dto.request.UpdateCompanyRequestDto;
//import com.team1.dto.request.UpdateWorkerRequestDto;
import com.team1.dto.request.PermitDto;
import com.team1.dto.request.WorkerDto;
import com.team1.repository.entity.Permit;
import com.team1.service.PermitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.team1.constant.EndPoints.*;
@RestController
@RequestMapping(PERMIT)
@RequiredArgsConstructor
public class PermitController {

    private final PermitService permitService;

    @PostMapping("/permit-create")
    public ResponseEntity<Boolean> createWorker(@RequestParam String token, @RequestBody PermitDto dto){
        return ResponseEntity.ok(permitService.createPermitUser(token, dto));
    }


    @GetMapping(FINDALL_PERMIT)
    public ResponseEntity<List<Permit>> findAllWorker(){
        return ResponseEntity.ok(permitService.findAllPermit());
    }

    @DeleteMapping(DELETE_BY_ID_PERMIT)
    public ResponseEntity<Boolean> deletePermit(String token, String workerId){
        return ResponseEntity.ok(permitService.deletePermit(token, workerId));
    }
}
