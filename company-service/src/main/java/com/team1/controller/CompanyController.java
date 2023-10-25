package com.team1.controller;
import com.team1.dto.request.RegisterRequestCompanyDto;
import com.team1.dto.response.RegisterResponseCompanyDto;
import com.team1.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<Boolean> register(@RequestBody @Valid RegisterRequestCompanyDto dto){
        return ResponseEntity.ok(companyService.register(dto));
    }
}
