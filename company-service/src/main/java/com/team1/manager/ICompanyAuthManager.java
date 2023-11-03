package com.team1.manager;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import static com.team1.constant.EndPoints.REGISTER;
import static com.team1.constant.EndPoints.SAVE_COMPANY;

@FeignClient(name = "auth-company-manager", url = "http://localhost:9090/api/v1/auth", decode404 = true)
public interface ICompanyAuthManager {

    @GetMapping("/hello")
    String hello();


}