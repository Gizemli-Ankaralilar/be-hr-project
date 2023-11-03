package com.team1.service;

import com.team1.dto.request.RegisterRequestCompanyDto;
import com.team1.dto.request.RegisterRequestVisitorDto;
import com.team1.dto.response.RegisterResponseVisitorDto;
import com.team1.exception.AuthManagerException;
import com.team1.exception.ErrorType;
import com.team1.manager.IUserProfileManager;
import com.team1.mapper.IAuthMapper;
import com.team1.rabbitmq.model.MailRegisterModel;
import com.team1.rabbitmq.model.SaveAuthModel;
import com.team1.rabbitmq.model.SaveCompanyModel;
import com.team1.rabbitmq.producer.MailRegisterProducer;
import com.team1.rabbitmq.producer.SaveAuthProducer;
import com.team1.rabbitmq.producer.SaveCompanyProducer;
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
    private final SaveAuthProducer saveAuthProducer;
    private final SaveCompanyProducer saveCompanyProducer;


    public AuthService(IAuthRepository authRepository, JwtTokenManager jwtTokenManager, IUserProfileManager userProfileManager, MailRegisterProducer mailProducer, SaveAuthProducer saveAuthProducer, SaveCompanyProducer saveCompanyProducer) {
        super(authRepository);
        this.authRepository = authRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.userProfileManager = userProfileManager;
        this.mailProducer = mailProducer;
        this.saveAuthProducer = saveAuthProducer;
        this.saveCompanyProducer = saveCompanyProducer;
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
        saveAuthProducer.convertAndSendUser(SaveAuthModel.builder().authId(auth.getId()).
                username(dto.getUsername()).lastName(dto.getLastName()).surName(dto.getSurName()).
                email(dto.getEmail()).phone(dto.getPhone()).password(dto.getPassword()).address(dto.getAddress()).build());

        RegisterResponseVisitorDto responseVisitorDto = IAuthMapper.INSTANCE.toRegisterResponseDto(auth);
        String token = jwtTokenManager.createToken(auth.getId())
                .orElseThrow(() -> new AuthManagerException(ErrorType.INVALID_TOKEN));
        responseVisitorDto.setToken(token);
        responseVisitorDto.setComment("Kullanıcı kaydınız başarı ile gerçekleşti.Active etmek için mailinizi kontrol ediniz");
        //SILERSEN OLURSUN-->Tamam ABLAAAA
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
        save(auth);
        saveAuthProducer.convertAndSendUser(SaveAuthModel.builder().authId(auth.getId()).
                username(dto.getUsername()).lastName(dto.getLastName()).surName(dto.getSurName()).
                email(dto.getEmail()).phone(dto.getPhone()).password(dto.getPassword()).address(dto.getAddress()).build());

        if (dto.getTaxNumber().isEmpty() && dto.getCompanyName().isEmpty()) {//Buradaki iki alan boşsa kullanıcı kaydı oluştulacak.
            RegisterResponseVisitorDto responseVisitorDto = IAuthMapper.INSTANCE.toRegisterResponseDto(auth);
            String token = jwtTokenManager.createToken(auth.getId())
                    .orElseThrow(() -> new AuthManagerException(ErrorType.INVALID_TOKEN));
            responseVisitorDto.setToken(token);
            responseVisitorDto.setComment("Tax number ve company name alanlarını boş bıraktığınız için Kullanıcı kaydınız başarı ile gerçekleşti.Active etmek için mailinizi kontrol ediniz");

            MailRegisterModel mailModel=IAuthMapper.INSTANCE.toMailModel(auth);
            mailModel.setToken(token);
            mailProducer.sendMail(mailModel);
            return responseVisitorDto;
        } else {//Burada company kuyruğu üretildi
            saveCompanyProducer.convertAndSendCompany(SaveCompanyModel.builder().authId(auth.getId()).
                    username(dto.getUsername()).lastName(dto.getLastName()).surName(dto.getSurName()).
                    email(dto.getEmail()).phone(dto.getPhone()).password(dto.getPassword()).address(dto.getAddress()).
                    companyName(dto.getCompanyName()).taxNumber(dto.getTaxNumber()).build());
            RegisterResponseVisitorDto responseVisitorDto = IAuthMapper.INSTANCE.toRegisterResponseDto(auth);
            String token = jwtTokenManager.createToken(auth.getId())
                    .orElseThrow(() -> new AuthManagerException(ErrorType.INVALID_TOKEN));
            responseVisitorDto.setToken(token);
            responseVisitorDto.setComment("Şirket kaydınız başarı ile alınmıştır.Hesabınız aktif edilmesi için admin onayını beklemelisiniz.");
            return responseVisitorDto;

        }

    }
//    public Boolean companySave(RegisterSaveCompanyDto dto) {
//        try {
//            Auth auth = IAuthMapper.INSTANCE.toAuthCompany(dto);
//            save(auth);
//            iUserProfileManager.save(IAuthMapper.INSTANCE.toUserSaveRequestDto(auth));
//            return true;
//        } catch (Exception e) {
//            throw new AuthManagerException(ErrorType.BAD_REQUEST);
//        }
//    }

//    public String login(LoginRequestDto dto) {
//        Optional<Auth> optionalAuth = authRepository.findOptionalByUsernameAndPassword(dto.getUsername(), dto.getPassword());
//        if (optionalAuth.isEmpty()) {
//            throw new AuthManagerException(ErrorType.LOGIN_ERROR);
//        }
//        if (!optionalAuth.get().getStatus().equals(EStatus.ACTIVE)) {
//            throw new AuthManagerException(ErrorType.ACCOUNT_NOT_ACTIVE);
//        }
//        if(!optionalAuth.get().getActivated()==true){
//            optionalAuth.get().setActivated(true);
//        } else {
//            throw new AuthManagerException(ErrorType.ALREADY_ACTIVE);
//        }
//        return jwtTokenManager.createTokenCompany(optionalAuth.get().getId(), optionalAuth.get().getRole(), optionalAuth.get().getCompanyId())
//                .orElseThrow(() -> new AuthManagerException(ErrorType.TOKEN_NOT_CREATED));
//    }

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