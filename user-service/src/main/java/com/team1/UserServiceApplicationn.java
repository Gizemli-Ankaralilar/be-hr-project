package com.team1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UserServiceApplicationn {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplicationn.class);
    }
}