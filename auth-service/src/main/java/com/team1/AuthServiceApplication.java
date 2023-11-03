package com.team1;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class AuthServiceApplication {

//    private final AuthService authService;
//
//    public AuthServiceApplication(AuthService authService) {
//        this.authService = authService;
//    }

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class);
    }
}


//    public void bizeBirAdminCekDemliOlsun() {
//        String adminUsername = "admin";
//        String adminPassword = "pass";
//
//
//        if (authService.getAuthByUsername(adminUsername) == null) {
//            Auth adminUser = new Auth();
//            adminUser.setUsername(adminUsername);
//            adminUser.setPassword(adminPassword);
//            adminUser.setRole(ERole.ADMIN);
//            adminUser.setStatus(EStatus.ACTIVE);
//
//            authService.save(adminUser);
//        }
//    }
//
//    @Component
//    public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {
//        @Override
//        public void onApplicationEvent(ContextRefreshedEvent event) {
//            bizeBirAdminCekDemliOlsun();
//        }
//    }


