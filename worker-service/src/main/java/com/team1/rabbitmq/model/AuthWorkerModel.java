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
public class AuthWorkerModel implements Serializable {
    private Long authId;
    private Long companyId;
    private String username;
    private String email;
    private String lastName;
    private String firstName;
    private String phone;
    private String address;
}
