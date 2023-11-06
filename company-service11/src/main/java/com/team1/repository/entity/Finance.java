package com.team1.repository.entity;

import lombok.AllArgsConstructor;
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
public class Finance extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long companyId;
    private String companyName;
    private String taxNumber;
    //Aylık hatırlatmalar
    private String employeeSalary;
    private String credit;
    //Senelik hatırlatmalar
    private String totalIncame;
    private String companyProfit;
    private String companyLoss;

}
