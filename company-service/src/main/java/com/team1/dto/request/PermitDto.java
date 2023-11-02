package com.team1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PermitDto {
    private String workerId;
    private String companyId;

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
