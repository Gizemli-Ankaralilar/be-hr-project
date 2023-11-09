package com.team1.repository.entity;

import com.team1.repository.enums.ERole;
import com.team1.repository.enums.EStatus;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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
    @Enumerated(EnumType.STRING)
    private ERole role;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserWish> receivedWishes;
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserWish> sentWishes;


}
