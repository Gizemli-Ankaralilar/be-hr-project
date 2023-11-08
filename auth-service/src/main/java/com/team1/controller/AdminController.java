package com.team1.controller;

import com.team1.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
//@PreAuthorize("hasAuthority('ADMIn')") //Security
public class AdminController {

    private final AdminService adminService;


    @GetMapping("/approvedCompanyOwner")
    public ResponseEntity<Boolean> approvedCompanyOwner(){
        return ResponseEntity.ok(false);
         }




    }



