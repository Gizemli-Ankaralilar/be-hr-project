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
public class CompanyWorkerAuthModel implements Serializable {
    private Long companyId;
    private String username;
    private String email;
    private String lastName;
    private String firstName;
    private String phone;
    private String address;
    private String password;
    private ERole role;
}
