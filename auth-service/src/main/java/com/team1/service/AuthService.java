package com.team1.service;

import com.team1.dto.request.LoginRequestDto;
import com.team1.dto.request.RegisterRequestCompanyDto;
import com.team1.dto.request.RegisterRequestVisitorDto;
import com.team1.dto.request.SendMailRequestDto;
import com.team1.dto.response.RegisterResponseVisitorDto;
import com.team1.exception.AuthManagerException;
import com.team1.exception.ErrorType;
import com.team1.manager.IMailManager;
import com.team1.manager.IUserProfileManager;
import com.team1.mapper.IAuthMapper;
import com.team1.rabbitmq.model.*;
import com.team1.rabbitmq.producer.AuthCompanyProducer;
import com.team1.rabbitmq.producer.AuthMailProducer;
import com.team1.rabbitmq.producer.AuthUserProducer;
import com.team1.rabbitmq.producer.AuthWorkerProducer;
import com.team1.repository.IAuthRepository;
import com.team1.repository.entity.Auth;
import com.team1.repository.enums.ERole;
import com.team1.repository.enums.EStatus;
import com.team1.utility.CodeGenerator;
import com.team1.utility.JwtTokenManager;
import com.team1.utility.ServiceManager;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth, Long> {

    private final IAuthRepository authRepository;
    private final JwtTokenManager jwtTokenManager;
    private final IUserProfileManager userProfileManager;
    private final AuthUserProducer authUserProducer;
    private final AuthCompanyProducer authCompanyProducer;
    private final AuthWorkerProducer authWorkerProducer;
    private final AuthMailProducer authMailProducer;
    private final IMailManager iMailManager;


    public AuthService(IAuthRepository authRepository,
                       IMailManager iMailManager,
                       JwtTokenManager jwtTokenManager, IUserProfileManager userProfileManager,
                       AuthUserProducer authUserProducer, AuthCompanyProducer authCompanyProducer, AuthWorkerProducer authWorkerProducer, AuthMailProducer authMailProducer) {
        super(authRepository);
        this.authRepository = authRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.userProfileManager = userProfileManager;
        this.authUserProducer = authUserProducer;
        this.authCompanyProducer = authCompanyProducer;
        this.authWorkerProducer = authWorkerProducer;
        this.iMailManager = iMailManager;
        this.authMailProducer = authMailProducer;
    }

    @Transactional
    public RegisterResponseVisitorDto register(RegisterRequestVisitorDto dto) {

        if (authRepository.existsByUsername(dto.getUsername())) {
            throw new AuthManagerException(ErrorType.USERNAME_ALREADY_EXIST);
        }
        Auth auth = IAuthMapper.INSTANCE.toRegisterAuth(dto);
        auth.setActivationCode(CodeGenerator.generateCode());
        save(auth);
        //dto dan gelen verileri de modele eklediğimiz için mapper kullanamadık.
        authUserProducer.createUser(AuthUserModel.builder().authId(auth.getId()).phone(dto.getPhone()).
                address(dto.getAddress()).email(dto.getEmail()).role(auth.getRole()).firstName(dto.getFirstName()).
                lastName(dto.getLastName()).username(dto.getUsername()).role(auth.getRole()).build());

        RegisterResponseVisitorDto responseVisitorDto = IAuthMapper.INSTANCE.toRegisterResponseDto(auth);
        String token = jwtTokenManager.createToken(auth.getId())
                .orElseThrow(() -> new AuthManagerException(ErrorType.INVALID_TOKEN));
        responseVisitorDto.setToken(token);
        responseVisitorDto.setComment("Kullanıcı kaydınız başarı ile gerçekleşti.Active etmek için mailinizi kontrol ediniz");

        //authMailProducer.sendActivationCode(AuthMailModel.builder().username(auth.getUsername()).email(auth.getEmail()).token(token).build());
        SendMailRequestDto sendMailRequestDto = IAuthMapper.INSTANCE.toSendMailRequestDto(auth);
        sendMailRequestDto.setToken(token);
        iMailManager.sendMail(sendMailRequestDto);

        return responseVisitorDto;
    }

    public RegisterResponseVisitorDto companyRegister(RegisterRequestCompanyDto dto) {
        if (authRepository.existsByUsername(dto.getUsername())) {
            throw new AuthManagerException(ErrorType.USERNAME_ALREADY_EXIST);
        }
        Auth auth;
        if (dto.getTaxNumber().isEmpty() || dto.getCompanyName().isEmpty()) {
            auth = IAuthMapper.INSTANCE.toRegisterCompany(dto);
            auth.setActivationCode(CodeGenerator.generateCode());
            save(auth);
            authUserProducer.createUser(AuthUserModel.builder().authId(auth.getId()).phone(dto.getPhone()).
                    address(dto.getAddress()).email(dto.getEmail()).role(auth.getRole()).firstName(dto.getFirstName()).
                    lastName(dto.getLastName()).username(dto.getUsername()).role(auth.getRole()).build());
            RegisterResponseVisitorDto responseVisitorDto = IAuthMapper.INSTANCE.toRegisterResponseDto(auth);
            String token = jwtTokenManager.createToken(auth.getId())
                    .orElseThrow(() -> new AuthManagerException(ErrorType.INVALID_TOKEN));
            responseVisitorDto.setToken(token);
            responseVisitorDto.setComment("Tax number ve company name girmediğiniz için kullanıcı kaydınız başarı ile gerçekleşti.Active etmek için mailinizi kontrol ediniz");
            return responseVisitorDto;
        }else {//Company e gönderilecek yer
            auth = IAuthMapper.INSTANCE.toRegisterCompany(dto);
            auth.setRole(ERole.COMPANY_OWNER);
            save(auth);
            authUserProducer.createUser(AuthUserModel.builder().authId(auth.getId()).phone(dto.getPhone()).
                    address(dto.getAddress()).email(dto.getEmail()).firstName(dto.getFirstName()).
                    lastName(dto.getLastName()).username(dto.getUsername()).role(ERole.COMPANY_OWNER).build());
            authCompanyProducer.authCompany(AuthCompanyModel.builder().authId(auth.getId()).companyPhoneNumber(dto.getPhone()).
                    companyAddress(dto.getAddress()).taxNumber(dto.getTaxNumber()).companyName(dto.getCompanyName()).build());
            RegisterResponseVisitorDto responseVisitorDto = IAuthMapper.INSTANCE.toRegisterResponseDto(auth);
            String token = jwtTokenManager.createToken(auth.getId())
                    .orElseThrow(() -> new AuthManagerException(ErrorType.INVALID_TOKEN));
            responseVisitorDto.setToken(token);
            responseVisitorDto.setComment("Company kaydınız ile gerçekleşti.Company onayınızı admin yapacaktık.");
            return responseVisitorDto;

        }
    }

    public String login(LoginRequestDto dto) {
        Optional<Auth> optionalAuth = authRepository.findOptionalByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (optionalAuth.isEmpty()) {
            throw new AuthManagerException(ErrorType.LOGIN_ERROR);
        }
        if (!optionalAuth.get().getStatus().equals(EStatus.ACTIVE)) {
            throw new AuthManagerException(ErrorType.ACCOUNT_NOT_ACTIVE);
        }
        if(!optionalAuth.get().getLogged()==true){
            optionalAuth.get().setLogged(true);
            update(optionalAuth.get());//BURADA UPDATE İŞLEMİ GERÇEKLEŞİCEK AMA NASIL TAM BİLMİYORUM.TEST EDEMEDİM
        } else {
            throw new AuthManagerException(ErrorType.ALREADY_LOGGED);
        }
        return jwtTokenManager.createToken(optionalAuth.get().getId(), optionalAuth.get().getRole())
                .orElseThrow(() -> new AuthManagerException(ErrorType.TOKEN_NOT_CREATED));
    }
    //BURASI SONRASINDA SİLİNECEK

    @Transactional
    public String activateStatus(String token) {

        Optional<Long> id = jwtTokenManager.getIdFromToken(token);
        if (id.isEmpty()) {
            throw new AuthManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<Auth> optionalAuth = findById(id.get());
        if (optionalAuth.isEmpty()) {
            throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
        }
        if (optionalAuth.get().getStatus().equals(EStatus.ACTIVE)) {
            throw new AuthManagerException(ErrorType.ALREADY_ACTIVE);
        }
        optionalAuth.get().setStatus(EStatus.ACTIVE);
        update(optionalAuth.get());
        return "Hesabınız aktive edilmiştir";
    }

    //WORKER IN AUTH A KAYIT EKLENDİĞİ YER
    public void createWorkerAuth(CompanyWorkerAuthModel model) {
        Auth auth = IAuthMapper.INSTANCE.toRegisterCompany(model);
        auth.setRole(ERole.WORKER);
        save(auth);
        //authUserProducer.createUser(AuthUserModel.builder().authId(auth.getId()).build());
        authUserProducer.createUser(AuthUserModel.builder().authId(auth.getId()).role(auth.getRole()).
                companyId(model.getCompanyId()).username(model.getUsername()).email(model.getEmail()).
                lastName(model.getLastName()).firstName(model.getFirstName()).address(model.getAddress()).
                phone(model.getPhone()).build());
        authWorkerProducer.authWorker(AuthWorkerModel.builder().authId(auth.getId()).address(model.getAddress()).
                companyId(model.getCompanyId()).username(model.getUsername()).email(model.getEmail()).
                lastName(model.getLastName()).firstName(model.getFirstName()).phone(model.getPhone()).
                address(model.getAddress()).build());
    }

    public Auth getAuthByUsername(String username) {
        Optional<Auth> auth = authRepository.findAuthByUsername(username);
        return auth.orElse(null);
    }
}