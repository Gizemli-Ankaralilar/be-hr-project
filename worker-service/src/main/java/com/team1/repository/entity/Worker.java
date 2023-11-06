package com.team1.repository.entity;

import com.team1.repository.enums.EFieldOfWork;
import com.team1.repository.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Entity
public class Worker extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long authId;//5
    private Long userId;
    private Long companyId;//1
    private String username;
    private String email;
    private String password;
    @Builder.Default
    private ERole role = ERole.WORKER;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String userAvatar;
    @Builder.Default
    private EFieldOfWork fieldOfWork = EFieldOfWork.BOS;//ENUM DEĞERLERİ GİRİLECEK//izinleri olabilir
}
