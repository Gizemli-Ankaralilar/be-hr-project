package com.team1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class EmailController {

  /*  EmailSenderService emailSenderService;
    @PostMapping()
    @Operation(summary = "Kullanici kayit eden metot")
    public ResponseEntity<RegisterResponseDto> registerWithRabbitMq(@RequestBody @Valid RegisterRequestDto dto){
        return  ResponseEntity.ok( emailSenderService.registerWithRabbitMQ(dto));
    }*/
}
