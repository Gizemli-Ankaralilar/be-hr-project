package com.team1.controller;

import com.team1.dto.request.RegisterRequestCompanyDto;
import com.team1.dto.request.RegisterRequestVisitorDto;
import com.team1.dto.response.RegisterResponseVisitorDto;
import com.team1.service.AuthService;
import com.team1.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import  static com.team1.constant.EndPoints.*;

import javax.validation.Valid;


@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final JwtTokenManager jwtTokenManager;

    @PostMapping(VISITOR_REGISTER)
    public ResponseEntity<RegisterResponseVisitorDto> register(@RequestBody @Valid RegisterRequestVisitorDto dto){
        return ResponseEntity.ok(authService.register(dto));
    }

    @PostMapping(REGISTER_COMPANY)
    public ResponseEntity<RegisterResponseVisitorDto> companyRegister(@RequestBody @Valid RegisterRequestCompanyDto dto){
        return ResponseEntity.ok(authService.companyRegister(dto));
    }
//
//    @PostMapping(LOGIN)
//    public ResponseEntity<String> login(@RequestBody LoginRequestDto dto){
//        return ResponseEntity.ok(authService.login(dto));
//    }

    @GetMapping(ACTIVATE_STATUS)
    public ResponseEntity<String> activateStatus(@RequestParam String token){
        return ResponseEntity.ok(authService.activateStatus(token));
    }

    @GetMapping("/create_token")
    public  ResponseEntity<String> createToken(Long id){
        return ResponseEntity.ok(jwtTokenManager.createToken(id).get());
    }

    @GetMapping("/get_id_from_token")
    public  ResponseEntity<Long> getIdFromToken(String token){
        return ResponseEntity.ok(jwtTokenManager.getIdFromToken(token).get());
    }
}
