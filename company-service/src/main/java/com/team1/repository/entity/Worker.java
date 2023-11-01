package com.team1.repository.entity;

import com.team1.repository.enums.EFieldOfWork;
import com.team1.repository.enums.EPermission;
import com.team1.repository.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Document
public class Worker extends BaseEntity{
    @Id
    private String id;//worker ıd'yi göndericem ve companyıd'yi ve authId'yi çekicem
    private Long authId;//5
    private String companyId;//1
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
    @Builder.Default
    private EPermission permission = EPermission.NO_LEAVE;
    //private List<String> breakTime;

}
