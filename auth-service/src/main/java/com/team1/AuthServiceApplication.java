package com.team1;

import com.team1.repository.entity.Auth;
import com.team1.repository.enums.ERole;
import com.team1.repository.enums.EStatus;
import com.team1.service.AuthService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableFeignClients
public class AuthServiceApplication {

    private final AuthService authService;

    public AuthServiceApplication(AuthService authService) {
        this.authService = authService;
    }

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class);
    }


/// AŞAĞISI BAŞLANGIÇTA BİR ADMIN OLUŞTURMAK İÇİN!!!

    public void bizeBirAdminCekDemliOlsun() {
        String adminUsername = "admin";
        String adminPassword = "pass";

        if (authService.getAuthByUsername(adminUsername) == null) {
            Auth adminUser = new Auth();
            adminUser.setUsername(adminUsername);
            adminUser.setPassword(adminPassword);
            adminUser.setRole(ERole.ADMIN);
            adminUser.setStatus(EStatus.ACTIVE);

            authService.save(adminUser);
        }
    }
}

@Component
class ApplicationStartup {

    private final AuthService authService;

    public ApplicationStartup(AuthService authService) {
        this.authService = authService;
    }

    @PostConstruct
    public void init() {
        bizeBirAdminCekDemliOlsun();
    }

    private void bizeBirAdminCekDemliOlsun() {
        String adminUsername = "admin";
        String adminPassword = "pass";
        String adminEmail = "gizemliankaralilar@gmail.com";

        if (authService.getAuthByUsername(adminUsername) == null) {
            Auth adminUser = new Auth();
            adminUser.setUsername(adminUsername);
            adminUser.setPassword(adminPassword);
            adminUser.setRole(ERole.ADMIN);
            adminUser.setStatus(EStatus.ACTIVE);
            adminUser.setEmail(adminEmail);

            authService.save(adminUser);
        }
    }
}
