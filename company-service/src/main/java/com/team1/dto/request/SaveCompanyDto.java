package com.team1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SaveCompanyDto {

    private String username;
    private String email;
    private String password;
    private String companyName;
    private String taxNumber;
}
