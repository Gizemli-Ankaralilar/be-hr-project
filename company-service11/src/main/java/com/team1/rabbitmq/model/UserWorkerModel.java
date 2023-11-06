package com.team1.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserWorkerModel {
    private Long authId;//5
    private Long userId;
    private String companyId;//1
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
}
