package com.team1.service;

import com.team1.exception.CompanyException;
import com.team1.exception.ErrorType;
import com.team1.repository.IFinanceRepository;
import com.team1.repository.entity.Company;
import com.team1.repository.entity.Finance;
import com.team1.utility.JwtTokenManager;
import com.team1.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FinanceService extends ServiceManager<Finance, String>{

    private final JwtTokenManager jwtTokenManager;
    private final IFinanceRepository financeRepository;
    private Company company;

    public FinanceService(IFinanceRepository financeRepository, JwtTokenManager jwtTokenManager, IFinanceRepository financeRepository1) {
        super(financeRepository);
        this.jwtTokenManager = jwtTokenManager;
        this.financeRepository = financeRepository1;
    }

    /*public Boolean createFinance(String token, FinanceDto dto){
        String companyId = jwtTokenManager.getCompanyIdFromToken(token).orElseThrow(() -> {
            throw new CompanyException(ErrorType.INVALID_TOKEN);
        });
        Long authId = jwtTokenManager.getIdFromToken(token).orElseThrow(() -> {
            throw new CompanyException(ErrorType.INVALID_TOKEN);
        });
        Finance finance = Finance.builder().companyId(companyId).companyName(dto.getCompanyName())
                .taxNumber(dto.getTaxNumber()).build();

        save(finance);
        List<String> id = new ArrayList<>();
        if (company.getId().equals(companyId)) {//bu metot olmasa da olabilir.
            id.add(finance.getId());
            company.setFinances(id);
        }
        return true;
    }*/

    public List<Finance> findAllFinance() {
        return financeRepository.findAll();
    }

    public Boolean deleteFinance(String token, String financeId){//workerId frontand tarafından alınacak
        String companyId = jwtTokenManager.getCompanyIdFromToken(token).orElseThrow(() -> {
            throw new CompanyException(ErrorType.INVALID_TOKEN);
        });
        Optional<Finance> finance = financeRepository.findById(financeId);
        if (finance.get().getCompanyId().equals(companyId)) {
            financeRepository.delete(finance.get());
            return true;
        }
        return false;
    }

}