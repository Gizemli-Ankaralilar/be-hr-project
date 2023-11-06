package com.team1.dto.request;

import com.team1.repository.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterRequestVisitorDto {
    private String username;
    private String password;
    private String email;
    private String lastName;
    private String firstName;
    private String phone;
    private String address;
}
