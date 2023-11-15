package com.team1.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CompanyListenerResponseDto {
    private String companyName;
    private String companyAddress;
    private String companyPhoneNumber;
}