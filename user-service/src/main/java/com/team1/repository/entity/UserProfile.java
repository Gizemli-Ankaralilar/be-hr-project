package com.team1.repository.entity;



import com.team1.repository.enums.ERole;
import com.team1.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Entity
public class UserProfile extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long authId;
    private Long companyId;
    private String username;
    private String lastName;
    private String firstName;
    private String email;
    private String phone;
    private String address;
    private String avatar;
    private String about;
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EStatus status = EStatus.PENDING;
    @Builder.Default
    private ERole role;

}
