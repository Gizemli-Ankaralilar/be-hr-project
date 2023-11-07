package com.team1.manager;

import com.team1.dto.request.SendMailRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.team1.constant.EndPoints.SEND_MAIL;

@FeignClient(name = "auth-mail-profile", url = "http://localhost:7075/mail",
decode404 = true)
public interface IMailManager {

    @PostMapping(SEND_MAIL)
    ResponseEntity<String> sendMail(@RequestBody SendMailRequestDto dto);

}
