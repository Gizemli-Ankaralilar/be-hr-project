package com.team1.controller;
import com.team1.dto.request.*;
import com.team1.repository.entity.Company;
import com.team1.service.CompanyService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @GetMapping
    public String hello() {
        return "Company Service";
    }

    @PostMapping(REGISTER)
    public ResponseEntity<Boolean> register(@RequestBody @Valid SaveCompanyDto dto){
        return ResponseEntity.ok(companyService.register(dto));
    }

    @GetMapping(FIND_ALL)
    public ResponseEntity<List<Company>> findAll(){
        return ResponseEntity.ok(companyService.findAll());
    }



//    @PutMapping(UPDATE)
//    @CrossOrigin("*")
//    public ResponseEntity<Company> updateCompany(@PathVariable String taxNumber, @RequestBody UpdateCompanyRequestDto dto){
//        Company company = companyService.updateCompany(taxNumber, dto);
//        return new ResponseEntity<>(company, HttpStatus.OK);
//    }

//    @DeleteMapping(DELETE)
//    public ResponseEntity<Boolean> deleteCompany(@RequestBody @PathVariable String taxNumber){
//        return ResponseEntity.ok(companyService.deleteCompany(taxNumber));
//    }


}
