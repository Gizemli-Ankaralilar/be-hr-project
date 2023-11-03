package com.team1.controller;
import com.team1.repository.entity.Company;
import com.team1.request.SaveCompanyDto;
import com.team1.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import  static com.team1.constant.EndPoints.*;


@RestController
@RequestMapping(COMPANY)
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping(REGISTER)
    public ResponseEntity<Boolean> register(@RequestBody @Valid SaveCompanyDto dto){
        return ResponseEntity.ok(companyService.register(dto));
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
