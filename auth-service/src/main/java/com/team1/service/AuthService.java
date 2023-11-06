package com.team1.service;

import com.team1.dto.request.LoginRequestDto;
import com.team1.dto.request.RegisterRequestCompanyDto;
import com.team1.dto.request.RegisterRequestVisitorDto;
import com.team1.dto.response.RegisterResponseVisitorDto;
import com.team1.exception.AuthManagerException;
import com.team1.exception.ErrorType;
import com.team1.manager.IUserProfileManager;
import com.team1.mapper.IAuthMapper;
import com.team1.rabbitmq.model.AuthUserModel;
import com.team1.rabbitmq.model.MailRegisterModel;
import com.team1.rabbitmq.producer.AuthUserProducer;
import com.team1.rabbitmq.producer.MailRegisterProducer;
import com.team1.repository.IAuthRepository;
import com.team1.repository.entity.Auth;
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
    private final MailRegisterProducer mailProducer;
    private final AuthUserProducer authUserProducer;


    public AuthService(IAuthRepository authRepository, JwtTokenManager jwtTokenManager, IUserProfileManager userProfileManager, MailRegisterProducer mailProducer, AuthUserProducer authUserProducer) {
        super(authRepository);
        this.authRepository = authRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.userProfileManager = userProfileManager;
        this.mailProducer = mailProducer;
        this.authUserProducer = authUserProducer;
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
        //SILERSEN OLURSUN-->TAMAM ABLAAAAAAAAAAAA
        MailRegisterModel mailModel=IAuthMapper.INSTANCE.toMailModel(auth);
        mailModel.setToken(token);
        mailProducer.sendMail(mailModel);
        return responseVisitorDto;
    }

    public RegisterResponseVisitorDto companyRegister(RegisterRequestCompanyDto dto) {

        if (authRepository.existsByUsername(dto.getUsername())) {
            throw new AuthManagerException(ErrorType.USERNAME_ALREADY_EXIST);
        }
        Auth auth = IAuthMapper.INSTANCE.toRegisterCompany(dto);
        auth.setActivationCode(CodeGenerator.generateCode());
        return null;
    }

    public String login(LoginRequestDto dto) {
        Optional<Auth> optionalAuth = authRepository.findOptionalByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (optionalAuth.isEmpty()) {
            throw new AuthManagerException(ErrorType.LOGIN_ERROR);
        }
        if (!optionalAuth.get().getStatus().equals(EStatus.ACTIVE)) {
            throw new AuthManagerException(ErrorType.ACCOUNT_NOT_ACTIVE);
        }
        if(!optionalAuth.get().getActivated()==true){
            optionalAuth.get().setActivated(true);
        } else {
            throw new AuthManagerException(ErrorType.ALREADY_ACTIVE);
        }
        return jwtTokenManager.createToken(optionalAuth.get().getId(), optionalAuth.get().getRole())
                .orElseThrow(() -> new AuthManagerException(ErrorType.TOKEN_NOT_CREATED));
    }
    //bURASI SONRASINDA SİLİNECEK

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


}