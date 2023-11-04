package com.team1.service;

import com.team1.dto.request.SaveWorkerDto;
import com.team1.exception.CompanyException;
import com.team1.exception.ErrorType;
import com.team1.mapper.ICompanyMapper;
import com.team1.rabbitmq.model.SaveCompanyModel;
import com.team1.rabbitmq.model.SaveWorkerModel;
import com.team1.rabbitmq.producer.WorkerSaveProducer;
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
    private final WorkerSaveProducer workerSaveProducer;


    public CompanyService(ICompanyRepository companyRepository, JwtTokenManager jwtTokenManager, ICompanyMapper companyMapper, WorkerSaveProducer workerSaveProducer) {
        super(companyRepository);
        this.companyRepository = companyRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.companyMapper = companyMapper;
        this.workerSaveProducer = workerSaveProducer;
    }
// {isim.soyisim@şirketismi.com} mail adresi ve şifre sistem tarafından oluşturulacaktır.
    //Login olunan token ile authId bilgi alınacak.Oradan company tablosuna sorgu çekilecek.
    //NoSql o sorguda o authId'ye sahip tüm kayıtları getireceği için orada companyId'ye de ulaşılabilcek.
    public String saveWorker(SaveWorkerDto dto) {
        //Bu metot sallantıda..Olmayabilir!!!!
        if (companyRepository.existsByUsername(dto.getUsername())) {
            throw new CompanyException(ErrorType.USERNAME_ALREADY_EXIST);
        }
        Company company = new Company();
        String email = dto.getLastName() + dto.getSurName() + "@" + "şirketismi" + ".com";
        String password = GeneratePassword.generatePassword();
        //Worker önce auth tablosuna kayıt edilmeli.Oradan da authId ile birlikte worker kayıt edilmeli.
        //rabbitle workera gönderilecek.-->CompanyId ile gönderim sağlanması gerekli.
        workerSaveProducer.convertAndSendWorker(SaveWorkerModel.builder().companyId(company.getId()).
                username(dto.getUsername()).email(email).lastName(dto.getLastName()).surName(dto.getSurName()).
                phone(dto.getPhone()).address(dto.getAddress()).build());

        //rabbitle auth a gönderilecek.Burada workerId'yi authId'ye ekleyerek gönderme yaparsak sonrasında authId bilgisi çekilebilir.
        //

        return "Çalışan kaydetme işleminiz başarı ile gerçekleşmiştir.Kullanıcı aı ve şifresi";
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
