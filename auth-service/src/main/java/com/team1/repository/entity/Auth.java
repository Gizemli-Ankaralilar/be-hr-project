package com.team1.repository.entity;

import com.team1.repository.enums.ERole;
import com.team1.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Entity
public class Auth extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String email;
    private String activationCode;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ERole role = ERole.GUEST;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EStatus status = EStatus.PENDING;
    private Long companyId;
    private Long workerId;
    @Builder.Default
    private Boolean logged = false;
}