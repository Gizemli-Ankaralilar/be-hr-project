package com.team1.repository.entity;


import com.team1.repository.enums.ERole;
import com.team1.repository.enums.EStatus;
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
public class Company extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long authId;
    private String companyName;
    private String taxNumber;
    private String companyAddress;
    private String companyPhoneNumber;
    @Builder.Default
    private EStatus status = EStatus.INACTIVE;

}
