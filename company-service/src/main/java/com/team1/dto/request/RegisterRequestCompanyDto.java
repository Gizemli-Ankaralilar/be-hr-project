package com.team1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterRequestCompanyDto {
    private Long authId;
    private String username;
    private String password;
    private String companyName;
    private String taxNumber;
    private String companyEmail;
    private String companyAddress;
    private String companyPhoneNumber;

}
