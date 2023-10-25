package com.team1.manager;

import com.team1.dto.request.RegisterRequestCompanyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import static com.team1.constant.EndPoints.REGISTER;

@FeignClient(url = "http://localhost:9092/api/v1/company")
public interface ICompanyManager {

    @PostMapping(REGISTER)
    ResponseEntity<Boolean> register(@RequestBody @Valid RegisterRequestCompanyDto dto);
}
