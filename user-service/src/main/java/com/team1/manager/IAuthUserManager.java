package com.team1.manager;

import com.team1.dto.request.SaveUserRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import static com.team1.constant.EndPoints.SAVE;


public interface IAuthUserManager {

    @GetMapping("/hello")
    String hi();

    @PostMapping(SAVE)
    ResponseEntity<Boolean> save(@RequestBody @Valid SaveUserRequestDto dto);
}
