package com.team1.controller;

import com.team1.dto.request.SendMailRequestDto;
import com.team1.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.team1.constant.EndPoints.*;

@RestController
@RequestMapping(MAIL)
@RequiredArgsConstructor
public class MailController {
    private final MailService userService;

    @PostMapping(SEND_MAIL)
    public ResponseEntity<String> sendMail(@RequestBody SendMailRequestDto dto) {
        return ResponseEntity.ok(userService.sendMail(dto));
    }

}
