package com.team1.manager;

import com.team1.dto.request.RegisterRequestUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.team1.constant.EndPoints.*;

@FeignClient(name = "user-profile-manager", url = "http://localhost:9094/user",
decode404 = true)
public interface IUserProfileManager {

    @PostMapping(SAVE)
    ResponseEntity<Boolean> save(@RequestBody RegisterRequestUserDto dto);
}