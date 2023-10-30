package com.team1.service;

import com.team1.dto.request.*;
import com.team1.exception.CompanyException;
import com.team1.exception.ErrorType;
import com.team1.manager.ICompanyAuthManager;
import com.team1.mapper.ICompanyMapper;
import com.team1.mapper.ISetCompanyMapper;
import com.team1.repository.*;
import com.team1.repository.entity.Company;
import com.team1.repository.enums.EStatus;
import com.team1.utility.CodeGenerator;
import com.team1.utility.JwtTokenManager;
import com.team1.utility.ServiceManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService  extends ServiceManager<Company, String> {


    private final JwtTokenManager jwtTokenManager;
    private final ICompanyAuthManager companyAuthManager;



    // APO EKLEDİİ
    private final ICompanyRepository companyRepository;



    public CompanyService(ICompanyRepository companyRepository, JwtTokenManager jwtTokenManager, ICompanyAuthManager companyAuthManager, ISetCompanyMapper iSetCompanyMapper, ICommentRepository commentRepository, IFinanceRepository financeRepository, IPermissionRepository permissionRepository, IWorkerRepository workerRepository) {
        super(companyRepository);
        this.companyRepository = companyRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.companyAuthManager = companyAuthManager;
    }



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


    public String activateStatus(ActivateRequestDto dto) {
        Optional<Long> id = jwtTokenManager.getIdFromToken(dto.getToken());
        if (id.isEmpty()) {
            throw new CompanyException(ErrorType.INVALID_TOKEN);
        }
        Optional<Company> optionalCompany = findById((id.get()).toString());
        if (optionalCompany.isEmpty()) {
            throw new CompanyException(ErrorType.USER_NOT_FOUND);
        }
        if (optionalCompany.get().getStatus().equals(EStatus.ACTIVE)) {
            throw new CompanyException(ErrorType.ALREADY_ACTIVE);
        }
        if (dto.getActivationCode().equals(optionalCompany.get().getActivationCode())) {
            optionalCompany.get().setStatus(EStatus.ACTIVE);
            update(optionalCompany.get());
            // userManager.activateStatus(dto.getToken()); // open feign ile haberleşme
            //activationProducer.activateStatus(dto.getToken()); // rabbitmq ile haberleşme
            return "Hesabınız aktive edilmiştir";
        } else {
            throw new CompanyException(ErrorType.INVALID_CODE);
        }
    }





    // APOOOO EKLEDİİİ





    @Transactional(readOnly = true)
    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    public Company updateCompany(String taxNumber, UpdateCompanyRequestDto dto){
        Company company = companyRepository.findByTaxNumber(taxNumber)
                .orElseThrow(() -> new CompanyException(ErrorType.COMPANY_NOT_FOUND));
        company.setCompanyName(dto.getCompanyName());
        company.setTaxNumber(dto.getTaxNumber());
        company.setEmail(dto.getEmail());
        company.setUpdateDate(company.getUpdateDate());
        return companyRepository.save(company);
    }

    @Transactional
    public Boolean deleteCompany(String taxNumber){
        Optional<Company> company = companyRepository.findByTaxNumber(taxNumber);
        if (company.isEmpty()){
            throw new CompanyException(ErrorType.COMPANY_NOT_FOUND);
        }
        companyRepository.delete(company.get());
        return true;
    }
}
