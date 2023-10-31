package com.team1.repository.entity;

import com.team1.repository.enums.EFieldOfWork;
import com.team1.repository.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Document
public class Worker extends BaseEntity{
    @Id
    private String id;//worker ıd'yi göndericem ve companyıd'yi ve authId'yi çekicem
    private String authId;
    private String password;
    @Builder.Default
    private ERole role = ERole.WORKER;
    private String activationCode;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String userAvatar;
    @Builder.Default
    private EFieldOfWork fieldOfWork = EFieldOfWork.BOS;//ENUM DEĞERLERİ GİRİLECEK
    //izinleri olabilir


}
