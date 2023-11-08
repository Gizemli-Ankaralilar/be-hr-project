package com.team1.service;

import com.team1.dto.request.IncomeDto;
import com.team1.dto.request.SpendingDto;
import com.team1.dto.response.ResponceIncomeDto;
import com.team1.dto.response.ResponseSpendingDto;
import com.team1.mapper.ICompanyMapper;
import com.team1.repository.ICompanyRepository;
import com.team1.repository.IFinanceRepository;
import com.team1.repository.entity.Finance;
import com.team1.utility.JwtTokenManager;
import com.team1.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FinanceService extends ServiceManager<Finance, Long> {

    private final JwtTokenManager jwtTokenManager;
    private final IFinanceRepository financeRepository;
    private final ICompanyRepository companyRepository;
    private final ICompanyMapper companyMapper;

    public FinanceService(IFinanceRepository financeRepository, JwtTokenManager jwtTokenManager, ICompanyRepository companyRepository, ICompanyMapper companyMapper) {
        super(financeRepository);
        this.financeRepository = financeRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    public ResponseSpendingDto spending(String token, SpendingDto dto) {
        Optional<Long> authIdToken = jwtTokenManager.getIdFromToken(token);
        System.out.println(authIdToken.get());
        Optional<Long> companyId = companyRepository.companyById(authIdToken.get());
        System.out.println(companyId.get());
        Finance finance = Finance.builder().companyId(companyId.get()).
        spending(dto.getSpending()).spendingAmount(dto.getSpendingAmount()).build();
        save(finance);
        ResponseSpendingDto responseAmountDto = ICompanyMapper.INSTANCE.toSpendingFinance(finance);
        return responseAmountDto;
    }

    public ResponceIncomeDto income(String token, IncomeDto dto) {
        Optional<Long> authIdToken = jwtTokenManager.getIdFromToken(token);
        System.out.println(authIdToken.get());
        Optional<Long> companyId = companyRepository.companyById(authIdToken.get());
        System.out.println(companyId.get());
        Finance finance = Finance.builder().companyId(companyId.get()).
                income(dto.getIncome()).incomeAmount(dto.getIncomeAmount()).build();
        save(finance);
        ResponceIncomeDto responceIncomeDto = ICompanyMapper.INSTANCE.toIncomeFinance(finance);
        return responceIncomeDto;
    }

    public String financialSituation(String token) {
        Optional<Long> authIdToken = jwtTokenManager.getIdFromToken(token);
        System.out.println(authIdToken.get());
        Optional<Long> companyId = companyRepository.companyById(authIdToken.get());
        System.out.println(companyId.get());
        Optional<Double> profitValue = financeRepository.profit(companyId.get());
        Optional<Double> lossValue = financeRepository.loss(companyId.get());
        double situation = profitValue.get() - lossValue.get();
        if (situation<0) {
            return "Şirketinizin zarar oranı " + situation;
        } else {
            return "Şirketiniz kar oranı " + situation;
        }
    }

    public Double incomeTotal(String token) {
        Optional<Long> authIdToken = jwtTokenManager.getIdFromToken(token);
        System.out.println(authIdToken.get());
        Optional<Long> companyId = companyRepository.companyById(authIdToken.get());
        System.out.println(companyId.get());
        Optional<Double> lossValue = financeRepository.loss(companyId.get());
        return lossValue.get();
    }
}
