package com.team1.service;

import com.team1.dto.request.SaveWorkerDto;
import com.team1.exception.CompanyException;
import com.team1.exception.ErrorType;
import com.team1.mapper.ICompanyMapper;
import com.team1.rabbitmq.model.AuthCompanyModel;
import com.team1.rabbitmq.model.CompanyMailModel;
import com.team1.rabbitmq.model.CompanyWorkerAuthModel;
import com.team1.rabbitmq.producer.CompanyMailProducer;
import com.team1.rabbitmq.producer.CompanyWorkerAuthProducer;
import com.team1.repository.*;
import com.team1.repository.entity.Company;
import com.team1.utility.GeneratePassword;
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
    private final CompanyWorkerAuthProducer companyWorkerAuthProducer;
    private final CompanyMailProducer companyMailProducer;


    public CompanyService(ICompanyRepository companyRepository, JwtTokenManager jwtTokenManager, ICompanyMapper companyMapper, CompanyWorkerAuthProducer companyWorkerAuthProducer, CompanyMailProducer companyMailProducer) {
        super(companyRepository);
        this.companyRepository = companyRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.companyMapper = companyMapper;
        this.companyWorkerAuthProducer = companyWorkerAuthProducer;
        this.companyMailProducer = companyMailProducer;
    }
    public String saveWorker(SaveWorkerDto dto) {
        if (companyRepository.existsByUsername(dto.getUsername())) {
            throw new CompanyException(ErrorType.USERNAME_ALREADY_EXIST);
        }
        String email = dto.getLastName() + "." + dto.getFirstName() + "@" + "şirketismi" + ".com";
        String password = GeneratePassword.generatePassword();
        companyMailProducer.createCompanyMail(CompanyMailModel.builder().newMail(email).mail(dto.getEmail()).password(password).build());
        companyWorkerAuthProducer.createWorkerAuth(CompanyWorkerAuthModel.builder().companyId(1L).
                email(email).password(password).phone(dto.getPhone()).lastName(dto.getLastName()).
                firstName(dto.getFirstName()).address(dto.getAddress()).username(dto.getUsername()).build());
        return "Çalışan kaydetme işleminiz başarı ile gerçekleşmiştir.Kullanıcı aı ve şifresi";
    }
    public List<Company> findAllCompany() {
        return companyRepository.findAll();
    }

    public Company getCompanyInformation(String id) {
        Optional<Company> companyInformation = companyRepository.findById(id);
        return companyInformation.orElse(null);
    }

    public void createAuthCompany(AuthCompanyModel model) {
        Company company = Company.builder().authId(model.getAuthId()).build();
        save(company);
    }
}
