package com.team1.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterResponseCompanyDto {
    private String token;
    private Long id;
    private String username;
    private String password;
    private String comment;
}