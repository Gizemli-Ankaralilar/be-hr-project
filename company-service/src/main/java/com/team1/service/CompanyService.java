package com.team1.service;

import com.team1.mapper.ICompanyMapper;
import com.team1.rabbitmq.model.SaveCompanyModel;
import com.team1.repository.*;
import com.team1.repository.entity.Company;
import com.team1.utility.JwtTokenManager;
import com.team1.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService  extends ServiceManager<Company, String> {


    private final JwtTokenManager jwtTokenManager;
    private final ICompanyRepository companyRepository;
    private final ICompanyMapper companyMapper;


    public CompanyService(ICompanyRepository companyRepository, JwtTokenManager jwtTokenManager, ICompanyMapper companyMapper) {
        super(companyRepository);
        this.companyRepository = companyRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.companyMapper = companyMapper;
    }

    public List<Company> findAllCompany() {
        return companyRepository.findAll();
    }

    public Company getCompanyInformation(String id) {
        Optional<Company> companyInformation = companyRepository.findById(id);
        return companyInformation.orElse(null);
    }

    public void saveCompanyRabbit(SaveCompanyModel model) {
        Company company = ICompanyMapper.INSTANCE.toSaveCompanyRabbit(model);
        save(company);
        //company bilgileri ile birlikte Admin onaya gider.
    }

    //worker kayıt işlemi
}
