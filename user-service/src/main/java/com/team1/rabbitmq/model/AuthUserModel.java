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
public class AuthUserModel implements Serializable {
    private Long authId;
    private String username;
    private String email;
    private ERole role;
    private Long companyId;
    private Long workerId;
    private String lastName;
    private String firstName;
    private String phone;
    private String address;
}
