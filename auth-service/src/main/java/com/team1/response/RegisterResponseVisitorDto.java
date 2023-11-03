package com.team1.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterResponseVisitorDto {
    private String token;
    private Long id;
    private String username;
}