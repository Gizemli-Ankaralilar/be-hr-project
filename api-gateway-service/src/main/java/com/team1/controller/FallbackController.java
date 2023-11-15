package com.team1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/auth_service")
    public ResponseEntity<String> authServiceFallback(){
        return ResponseEntity.ok("Auth Service şuanda hizmet veremiyor..");
    }

    @GetMapping("/user_service")
    public ResponseEntity<String> userServiceFallback(){
        return ResponseEntity.ok("User Service şuanda hizmet veremiyor..");
    }

    @GetMapping("/worker_service")
    public ResponseEntity<String> workerServiceFallback(){
        return ResponseEntity.ok("Worker Service şuanda hizmet veremiyor..");
    }

    @GetMapping("/company_service")
    public ResponseEntity<String> companyServiceFallback(){
        return ResponseEntity.ok("Company Service şuanda hizmet veremiyor..");
    }

    @GetMapping("/mail_service")
    public ResponseEntity<String> mailServiceFallback(){
        return ResponseEntity.ok("Mail Service şuanda hizmet veremiyor..");
    }

}
