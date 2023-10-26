package com.team1.manager;

import com.team1.dto.request.RegisterRequestVisitorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import static com.team1.constant.EndPoints.REGISTER;

@FeignClient(name = "auth-profile-manager", url = "http://localhost:9090/api/v1/auth", decode404 = true)
public interface IAuthmanager {

    @GetMapping("/hello")
    String hello();

    @PostMapping(REGISTER)
    ResponseEntity<Boolean> register(@RequestBody @Valid RegisterRequestVisitorDto dto);

}
