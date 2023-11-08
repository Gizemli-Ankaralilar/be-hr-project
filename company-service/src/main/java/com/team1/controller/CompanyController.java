package com.team1.controller;
import com.team1.dto.request.SaveWorkerDto;
import com.team1.repository.entity.Company;
import com.team1.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import  static com.team1.constant.EndPoints.*;


@RestController
@RequestMapping(COMPANY)
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;


    @PostMapping(SAVE_WORKER)
    public ResponseEntity<String> saveWorker(@RequestParam String token, @RequestBody SaveWorkerDto dto) {
        return ResponseEntity.ok(companyService.saveWorker(token, dto));
    }
//FIND_ALL_WORKER
    @GetMapping(FIND_ALL_WORKER)
    public ResponseEntity<String/*List<Company>*/> findAllWorker(@RequestParam String token){
        return ResponseEntity.ok(companyService.findAllCompanyWorker(token));
    }
}
