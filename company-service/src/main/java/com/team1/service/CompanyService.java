package com.team1.service;

import com.team1.exception.CompanyException;
import com.team1.exception.ErrorType;
import com.team1.manager.ICompanyAuthManager;
import com.team1.mapper.ICompanyMapper;
import com.team1.mapper.ISetCompanyMapper;
import com.team1.rabbitmq.producer.CreateAuthProducer;
import com.team1.rabbitmq.producer.CreateCompanyAuthProduces;
import com.team1.repository.*;
import com.team1.repository.entity.Company;
import com.team1.repository.enums.EStatus;
import com.team1.request.ActivateRequestDto;
import com.team1.request.SaveCompanyDto;
import com.team1.utility.CodeGenerator;
import com.team1.utility.JwtTokenManager;
import com.team1.utility.ServiceManager;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService  extends ServiceManager<Company, String> {


    private final JwtTokenManager jwtTokenManager;
    private final ICompanyAuthManager companyAuthManager;
    private final ICompanyRepository companyRepository;
    private final CreateAuthProducer createAuthProducer;
    private final CreateCompanyAuthProduces createCompanyAuthProduces;

    public CompanyService(ICompanyRepository companyRepository, JwtTokenManager jwtTokenManager, ICompanyAuthManager companyAuthManager, ISetCompanyMapper iSetCompanyMapper, ICommentRepository commentRepository, IFinanceRepository financeRepository, IWorkerRepository workerRepository, CreateAuthProducer createAuthProducer, CreateCompanyAuthProduces createCompanyAuthProduces) {
        super(companyRepository);
        this.companyRepository = companyRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.companyAuthManager = companyAuthManager;
        this.createCompanyAuthProduces = createCompanyAuthProduces;
        this.createAuthProducer = createAuthProducer;
    }



    public Boolean register(@Valid SaveCompanyDto dto) {
        Company company = ICompanyMapper.INSTANCE.toCompany(dto);
        company.setActivationCode(CodeGenerator.generateCode());
        if(!dto.getTaxNumber().isEmpty()) {
            save(company);
            createCompanyAuthProduces.createCompanyAuth(ICompanyMapper.INSTANCE.toSaveCompanyRabbit(company));
        }else {
            createAuthProducer.createSaveAuth(ICompanyMapper.INSTANCE.toSaveAutRabbit(company));
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

    public List<Company> findAllCompany() {
        return companyRepository.findAll();
    }

}
