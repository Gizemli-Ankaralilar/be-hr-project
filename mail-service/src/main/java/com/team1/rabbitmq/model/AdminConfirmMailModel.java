package com.team1.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AdminConfirmMailModel implements Serializable {
    private String username;
    private String email;
    private String phone;
    private String address;
    private String companyName;
    private String taxNumber;
    private String token;
    private String adminMail;

}
