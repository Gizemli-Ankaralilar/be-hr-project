package com.team1.repository.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Entity
public class MailProfile{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long authId;
    private String username;
    private String email;
    //private String token; //Bu gerekli mi?
}
