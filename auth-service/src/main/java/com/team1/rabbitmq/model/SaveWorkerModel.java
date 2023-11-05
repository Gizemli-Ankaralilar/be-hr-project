package com.team1.rabbitmq.model;

import com.team1.repository.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SaveWorkerModel implements Serializable {
    private String companyId;
    private String username;
    private String password;
    private String email;
    private String lastName;
    private String surName;
    private String phone;
    private String address;
    private ERole role;
}
