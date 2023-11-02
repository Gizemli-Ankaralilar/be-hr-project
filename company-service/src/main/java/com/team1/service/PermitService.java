package com.team1.service;

import com.team1.dto.request.PermitDto;
import com.team1.dto.request.WorkerDto;
import com.team1.exception.CompanyException;
import com.team1.exception.ErrorType;
import com.team1.repository.IPermitRepository;
import com.team1.repository.entity.Company;
import com.team1.repository.entity.Permit;
import com.team1.repository.entity.Worker;
import com.team1.utility.JwtTokenManager;
import com.team1.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PermitService extends ServiceManager<Permit, String> {

    private final JwtTokenManager jwtTokenManager;
    private final IPermitRepository permitRepository;
    private Company company;

    public PermitService(IPermitRepository permitRepository, JwtTokenManager jwtTokenManager, IPermitRepository permitRepository1) {
        super(permitRepository);
        this.jwtTokenManager = jwtTokenManager;
        this.permitRepository = permitRepository1;
    }

    public Boolean createPermitUser(String token, PermitDto dto){
        String companyId = jwtTokenManager.getCompanyIdFromToken(token).orElseThrow(() -> {
            throw new CompanyException(ErrorType.INVALID_TOKEN);
        });
        Permit permit = Permit.builder().companyId(companyId)
                .annualLeave(dto.getAnnualLeave())
                .paternityLeave(dto.getPaternityLeave())
                .motherhoodLeave(dto.getMotherhoodLeave())
                .pregnancyLeave(dto.getPregnancyLeave())
                .noLeave(dto.getNoLeave())
                .unpaidLeave(dto.getUnpaidLeave())
                .sickLeave(dto.getSickLeave())
                .breakfastBreak(dto.getBreakfastBreak())
                .lunchBreak(dto.getLunchBreak())
                .afternoonBreak(dto.getAfternoonBreak())
                .build();
        save(permit);
        List<String> id = new ArrayList<>();
        if (company.getId().equals(companyId)) {//bu metot olmasa da olabilir.
            id.add(permit.getId());
            company.setPermissions(id);
        }
        return true;
    }

    public List<Permit> findAllPermit() {
        return permitRepository.findAll();
    }

    public Boolean deletePermit(String token, String workerId){//workerId frontand tarafından alınacak
        String companyId = jwtTokenManager.getCompanyIdFromToken(token).orElseThrow(() -> {
            throw new CompanyException(ErrorType.INVALID_TOKEN);
        });
        Optional<Permit> permit = permitRepository.findById(workerId);
        if (permit.get().getCompanyId().equals(companyId)) {
            permitRepository.delete(permit.get());
            return true;
        }
        return false;
    }

}

