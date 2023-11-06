package com.team1.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Entity
public class Permit extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long workerId;
    private Long companyId;//izni ekleyenin Id'si
    private Long annualLeave;
    private Long paternityLeave;
    private Long motherhoodLeave;
    private Long pregnancyLeave;
    private Long noLeave;
    private Long unpaidLeave;
    private Long sickLeave;
    //aralar dakika başından tutuldu...İncelenmesi gerekli
    private Long breakfastBreak;
    private Long lunchBreak;
    private Long afternoonBreak;

}
