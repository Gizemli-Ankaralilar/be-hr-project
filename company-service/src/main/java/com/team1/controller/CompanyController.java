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

    @GetMapping(FIND_ALL_COMPANY)
    public ResponseEntity<List<Company>> findAll(){
        return ResponseEntity.ok(companyService.findAllCompany());
    }

    @GetMapping("/{companyId}/information")
    public ResponseEntity<Company> getCompnayInformation(@PathVariable String id) {
        Company company = companyService.getCompanyInformation(id);
        if (company != null) {
            return ResponseEntity.ok(company);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
