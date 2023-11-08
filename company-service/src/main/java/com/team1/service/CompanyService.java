package com.team1.service;

import com.team1.dto.request.SaveWorkerDto;
import com.team1.exception.CompanyException;
import com.team1.exception.ErrorType;
import com.team1.mapper.ICompanyMapper;
import com.team1.rabbitmq.model.AuthCompanyModel;
import com.team1.rabbitmq.model.CompanyMailModel;
import com.team1.rabbitmq.model.CompanyWorkerAuthModel;
import com.team1.rabbitmq.model.CompanyWorkerTokenModel;
import com.team1.rabbitmq.producer.CompanyMailProducer;
import com.team1.rabbitmq.producer.CompanyWorkerAuthProducer;
import com.team1.rabbitmq.producer.CompanyWorkerTokenProducer;
import com.team1.repository.*;
import com.team1.repository.entity.Company;
import com.team1.utility.GeneratePassword;
import com.team1.utility.JwtTokenManager;
import com.team1.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService  extends ServiceManager<Company, Long> {


    private final JwtTokenManager jwtTokenManager;
    private final ICompanyRepository companyRepository;
    private final ICompanyMapper companyMapper;
    private final CompanyWorkerAuthProducer companyWorkerAuthProducer;
    private final CompanyMailProducer companyMailProducer;
    private final CompanyWorkerTokenProducer companyWorkerTokenProducer;


    public CompanyService(ICompanyRepository companyRepository, JwtTokenManager jwtTokenManager, ICompanyMapper companyMapper, CompanyWorkerAuthProducer companyWorkerAuthProducer, CompanyMailProducer companyMailProducer, CompanyWorkerTokenProducer companyWorkerTokenProducer) {
        super(companyRepository);
        this.companyRepository = companyRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.companyMapper = companyMapper;
        this.companyWorkerAuthProducer = companyWorkerAuthProducer;
        this.companyMailProducer = companyMailProducer;
        this.companyWorkerTokenProducer = companyWorkerTokenProducer;
    }
    public String saveWorker(String token, SaveWorkerDto dto) {
        Optional<Long> authIdToken = jwtTokenManager.getIdFromToken(token);
        System.out.println(authIdToken.get());
        Optional<Long> companyId = companyRepository.companyById(authIdToken.get());
        System.out.println(companyId.get());
        String email = dto.getLastName() + "." + dto.getFirstName() + "@" + "şirketismi" + ".com";
        String password = GeneratePassword.generatePassword();
        companyMailProducer.createCompanyMail(CompanyMailModel.builder().newMail(email).mail(dto.getEmail()).password(password).build());
        companyWorkerAuthProducer.createWorkerAuth(CompanyWorkerAuthModel.builder().companyId(1L).
                email(email).password(password).phone(dto.getPhone()).lastName(dto.getLastName()).
                firstName(dto.getFirstName()).address(dto.getAddress()).username(dto.getUsername()).build());
        return "Çalışan kaydetme işleminiz başarı ile gerçekleşmiştir.Kullanıcı aı ve şifresi";
    }

    public void createAuthCompany(AuthCompanyModel model) {
        Company company = ICompanyMapper.INSTANCE.toAuthCompany(model);
        save(company);
    }

    //Dönüş tipini düşünmek lazım.Orası önemli ve sıkıntılı
    public String findAllCompanyWorker(String token) {
        Optional<Long> authIdToken = jwtTokenManager.getIdFromToken(token);
        System.out.println(authIdToken.get());
        Optional<Long> companyId = companyRepository.companyById(authIdToken.get());
        //YUKARIDAKİ METOT İLE AUTHıD ARACILIĞI İLE COMPANYID YE ERİŞTİK.WORKER LİSTELEMESİ İCİN DE RABBİT E YÜKLEDİK.
        CompanyWorkerTokenModel companyWorkerTokenModel = CompanyWorkerTokenModel.builder().companyId(companyId.get()).build();
        companyWorkerTokenProducer.workerListener(companyWorkerTokenModel);
        return "Çalışan listeniz hazırlanmıştır";
    }
}
