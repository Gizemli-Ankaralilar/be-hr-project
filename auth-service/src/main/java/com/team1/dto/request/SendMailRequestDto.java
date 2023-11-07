package com.team1.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendMailRequestDto {

    private Long authId;
    private String username;
    private String email;
    private String token;

}
