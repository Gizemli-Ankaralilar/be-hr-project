package com.team1.rabbitmq.model;

import lombok.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthCompanyModel implements Serializable {
    private Long authId;
    private String companyAddress;
    private String companyPhoneNumber;
    private String taxNumber;
    private String companyName;
}
