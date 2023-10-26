package com.team1.controller;

import com.team1.service.UserService;
import com.team1.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.team1.constant.EndPoints.USER;


@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;



    @GetMapping("/hi")
    public String hi() {
        return "Hi: User Service";
    }
}
