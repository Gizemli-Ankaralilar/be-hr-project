package com.team1.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Document
public class Permit extends BaseEntity{
    private String id;
    private String workerId;
    private String companyId;//izni ekleyenin Id'si
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
