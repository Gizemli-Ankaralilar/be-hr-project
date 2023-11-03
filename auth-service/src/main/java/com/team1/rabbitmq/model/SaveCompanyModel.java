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
public class SaveCompanyModel implements Serializable {
    private Long authId;
    private String username;
    private String email;
    private String password;
    private String lastName;
    private String surName;
    private String phone;
    private String address;
    private String companyName;
    private String taxNumber;
}
