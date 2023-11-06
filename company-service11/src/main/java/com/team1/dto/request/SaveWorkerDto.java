package com.team1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SaveWorkerDto {
    private String username;
    private String email;
    private String lastName;
    private String surName;
    private String phone;
    private String address;
}
