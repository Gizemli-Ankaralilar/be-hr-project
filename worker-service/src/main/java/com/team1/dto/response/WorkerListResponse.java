package com.team1.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WorkerListResponse {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
}
