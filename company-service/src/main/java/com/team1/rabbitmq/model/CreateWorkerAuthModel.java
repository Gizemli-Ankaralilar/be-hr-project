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
public class CreateWorkerAuthModel implements Serializable {
    private String workerId;
    private String username;
    private String password;
    private String email;
}