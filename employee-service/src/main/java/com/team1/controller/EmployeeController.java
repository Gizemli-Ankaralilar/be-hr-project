package com.team1.controller;
import com.team1.dto.request.RegisterRequestEmployeeDto;
import com.team1.repository.entity.Employee;
import com.team1.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.team1.constant.EndPoints.EMPLOYEE;
import static com.team1.constant.EndPoints.REGISTER;


@RestController
@RequestMapping(EMPLOYEE)
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public String hello() {
        return "EMPLOYEE Service";
    }

    @PostMapping(REGISTER)
    public ResponseEntity<Boolean> register(@RequestBody @Valid RegisterRequestEmployeeDto dto){
        return ResponseEntity.ok(employeeService.register(dto));
    }
}
