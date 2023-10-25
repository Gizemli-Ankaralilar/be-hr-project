package com.team1.repository.entity;


import com.team1.repository.enums.EFieldOfWork;
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
public class Company extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String companyName;
    private String taxNumber;
    private String companyEmail;
    private String companyAddress;
    private String companyPhoneNumber;
    private String activationCode;//şirket için oluşturulan activate kod hem veri tabanına eklenir hemde maili gönderilir.
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EFieldOfWork fieldOfWork = EFieldOfWork.BOS;//ENUM DEĞERLERİ GİRİLECEK
    private String role = "COMPANY_OWNER";
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EStatus status = EStatus.INACTIVE;

}
