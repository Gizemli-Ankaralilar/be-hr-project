package com.team1.repository.entity;

import lombok.AllArgsConstructor;
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
public class Finance extends BaseEntity{

    @Id
    private String id;
    private String companyId;
    private String companyName;
    private String taxNumber;
    //Aylık hatırlatmalar
    private String employeeSalary;
    private String credit;
    //Senelik hatırlatmalar
    private List<String> totalExpense;
    private String totalIncame;
    private String companyProfit;
    private String companyLoss;
    private List<String> expenses;

}
