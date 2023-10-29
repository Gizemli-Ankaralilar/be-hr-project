package com.team1.service;

import com.team1.dto.request.ActivateRequestDto;
import com.team1.dto.request.RegisterSaveCompanyDto;
import com.team1.dto.request.SaveCompanyDto;
import com.team1.exception.CompanyManagerException;
import com.team1.exception.ErrorType;
import com.team1.manager.ICompanyAuthManager;
import com.team1.mapper.ICompanyMapper;
import com.team1.repository.entity.Company;
import com.team1.repository.entity.ICompanyRepository;
import com.team1.repository.enums.EStatus;
import com.team1.utility.CodeGenerator;
import com.team1.utility.JwtTokenManager;
import com.team1.utility.ServiceManager;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@Service
public class CompanyService  extends ServiceManager<Company, Long> {

    private final ICompanyRepository companyRepository;
    private final JwtTokenManager jwtTokenManager;

    private final ICompanyAuthManager companyAuthManager;


    public CompanyService(ICompanyRepository companyRepository, JwtTokenManager jwtTokenManager, ICompanyAuthManager companyAuthManager) {
        super(companyRepository);
        this.companyRepository = companyRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.companyAuthManager = companyAuthManager;
    }


    @Transactional
    public Boolean register(@Valid SaveCompanyDto dto) {
        Company company = ICompanyMapper.INSTANCE.toCompany(dto);
        company.setActivationCode(CodeGenerator.generateCode());
        if(!dto.getTaxNumber().isEmpty()) {
            save(company);
            //companyAuthManager.companyRegister(ICompanyMapper.INSTANCE.toSaveCompany(company));
            companyAuthManager.companyRegister(RegisterSaveCompanyDto.builder().companyId(company.getId()).//comanyId geliyor ancak tabloya eklenmiyor
                    username(company.getUsername()).email(company.getEmail()).password(company.getPassword()).build());
        }else {

            //companyAuthManager.companyRegister(ICompanyMapper.INSTANCE.toSaveCompany(company));
            companyAuthManager.companyRegister(RegisterSaveCompanyDto.builder().
                    username(company.getUsername()).email(company.getEmail()).password(company.getPassword()).build());

        }
        //Burada kullanıcıya bilgilendirme yapılması gerekli.
        //Admin onayı gerekli yada taxnumber girilmediği için ziyaretçi kaydı oluturuldu gibi.
        return true;

    }

    @Transactional
    public String activateStatus(ActivateRequestDto dto) {
        Optional<Long> id = jwtTokenManager.getIdFromToken(dto.getToken());
        if (id.isEmpty()) {
            throw new CompanyManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<Company> optionalCompany = findById(id.get());
        if (optionalCompany.isEmpty()) {
            throw new CompanyManagerException(ErrorType.USER_NOT_FOUND);
        }
        if (optionalCompany.get().getStatus().equals(EStatus.ACTIVE)) {
            throw new CompanyManagerException(ErrorType.ALREADY_ACTIVE);
        }
        if (dto.getActivationCode().equals(optionalCompany.get().getActivationCode())) {
            optionalCompany.get().setStatus(EStatus.ACTIVE);
            update(optionalCompany.get());
            // userManager.activateStatus(dto.getToken()); // open feign ile haberleşme
            //activationProducer.activateStatus(dto.getToken()); // rabbitmq ile haberleşme
            return "Hesabınız aktive edilmiştir";
        } else {
            throw new CompanyManagerException(ErrorType.INVALID_CODE);
        }
    }
}
