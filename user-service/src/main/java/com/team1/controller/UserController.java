package com.team1.controller;
import com.team1.dto.request.SaveUserRequestDto;
import com.team1.repository.entity.UserProfile;
import com.team1.service.UserService;
import com.team1.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.team1.constant.EndPoints.*;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtTokenManager jwtTokenManager;

    @GetMapping("/hello")
    public String hi() {
        return "Hi: User Service";
    }

    @PostMapping(SAVE)
    public ResponseEntity<Boolean> save(@RequestBody @Valid SaveUserRequestDto dto){
        return ResponseEntity.ok(userService.saveUser(dto));
    }

//    @GetMapping("/{userId}/information")
//    public ResponseEntity<UserProfile> getUserInformation(@PathVariable Long userId) {
//        UserProfile userProfile = userService.getUserInformation(userId);
//        if (userProfile != null) {
//            return ResponseEntity.ok(userProfile);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

}
