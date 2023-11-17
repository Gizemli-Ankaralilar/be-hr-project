package com.team1.service;

import com.team1.dto.request.LoginRequestDto;
import com.team1.dto.request.LogoutRequestDto;
import com.team1.dto.request.RegisterRequestCompanyDto;
import com.team1.dto.request.RegisterRequestVisitorDto;
import com.team1.dto.response.RegisterResponseVisitorDto;
import com.team1.exception.AuthManagerException;
import com.team1.exception.ErrorType;
import com.team1.manager.IMailManager;
import com.team1.manager.IUserProfileManager;
import com.team1.mapper.IAuthMapper;
import com.team1.rabbitmq.model.*;
import com.team1.rabbitmq.producer.*;
import com.team1.repository.IAuthRepository;
import com.team1.repository.entity.Auth;
import com.team1.repository.enums.ERole;
import com.team1.repository.enums.EStatus;
import com.team1.utility.CodeGenerator;
import com.team1.utility.JwtTokenManager;
import com.team1.utility.ServiceManager;
import org.springdoc.core.fn.builders.apiresponse.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

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
    private final AdminConfirmMailProducer adminConfirmMailProducer;
    private final IMailManager iMailManager;

    //Admin icin degisiklikler basliyor burada
    private final AdminService adminService;



    public AuthService(IAuthRepository authRepository,
                       IMailManager iMailManager,
                       JwtTokenManager jwtTokenManager, IUserProfileManager userProfileManager,
                       AuthUserProducer authUserProducer, AuthCompanyProducer authCompanyProducer, AuthWorkerProducer authWorkerProducer, AuthMailProducer authMailProducer, AdminConfirmMailProducer adminConfirmMailProducer, AdminService adminService) {
        super(authRepository);
        this.authRepository = authRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.userProfileManager = userProfileManager;
        this.authUserProducer = authUserProducer;
        this.authCompanyProducer = authCompanyProducer;
        this.authWorkerProducer = authWorkerProducer;
        this.iMailManager = iMailManager;
        this.authMailProducer = authMailProducer;
        this.adminConfirmMailProducer = adminConfirmMailProducer;
        this.adminService = adminService;
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

        authMailProducer.sendActivationCode(AuthMailModel.builder().username(auth.getUsername()).email(auth.getEmail()).token(token).build());
//        SendMailRequestDto sendMailRequestDto = IAuthMapper.INSTANCE.toSendMailRequestDto(auth);
//        sendMailRequestDto.setToken(token);
//        iMailManager.sendMail(sendMailRequestDto);

        return responseVisitorDto;
    }

// Adminin mail ile company aktivasyonu yapabildiği metot.
    public RegisterResponseVisitorDto companyRegister(RegisterRequestCompanyDto dto) {
        if (authRepository.existsByUsername(dto.getUsername())) {
            throw new AuthManagerException(ErrorType.USERNAME_ALREADY_EXIST);
        }
        Auth auth = IAuthMapper.INSTANCE.toRegisterCompany(dto);
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
        responseVisitorDto.setComment("Company ön kaydınız gerçekleşti.Admin onayı bekleniyor.");

        adminConfirmMailProducer.sendActivationCode(AdminConfirmMailModel.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .companyName(dto.getCompanyName())
                .taxNumber(dto.getTaxNumber())
                .token(token)
                .adminMail(getAuthByUsername("admin").getEmail())
                .build());
        return responseVisitorDto;
    }


     /*   public RegisterResponseVisitorDto companyRegister(RegisterRequestCompanyDto dto) {

        if (authRepository.existsByUsername(dto.getUsername())) {
            throw new AuthManagerException(ErrorType.USERNAME_ALREADY_EXIST);
        }
        Auth auth;
        RegisterRequestVisitorDto registerRequestVisitorDto;
        if (dto.getTaxNumber().isEmpty() || dto.getCompanyName().isEmpty()) {

            registerRequestVisitorDto = IAuthMapper.INSTANCE.fromRequestCompanyDtoToRequestVisitorDto(dto);
            return register(registerRequestVisitorDto);

        } else {//Company e gönderilecek yer
            if(adminService.approvedCompanyOwner(dto)){
                //Admin onayladi
                auth = IAuthMapper.INSTANCE.toRegisterCompany(dto);
                auth.setRole(ERole.COMPANY_OWNER);
                save(auth);
                authUserProducer.createUser(AuthUserModel.builder().authId(auth.getId()).phone(dto.getPhone()).
                        address(dto.getAddress()).email(dto.getEmail()).role(auth.getRole()).firstName(dto.getFirstName()).
                        lastName(dto.getLastName()).username(dto.getUsername()).role(ERole.COMPANY_OWNER).build());
                authCompanyProducer.authCompany(AuthCompanyModel.builder().authId(auth.getId()).build());
                RegisterResponseVisitorDto responseVisitorDto = IAuthMapper.INSTANCE.toRegisterResponseDto(auth);
                String token = jwtTokenManager.createToken(auth.getId())
                        .orElseThrow(() -> new AuthManagerException(ErrorType.INVALID_TOKEN));
                responseVisitorDto.setToken(token);
                responseVisitorDto.setComment("Approved by Admin");
                return responseVisitorDto;
            }else{//Admin onaylamadigi durumda yine kullanici olarak kayit olacak
                //Admin den onay almadiniz uzgunuz
                registerRequestVisitorDto = IAuthMapper.INSTANCE.fromRequestCompanyDtoToRequestVisitorDto(dto);
                return register(registerRequestVisitorDto);
            }
        }
    }
*/

//    public RegisterResponseVisitorDto companyRegister(RegisterRequestCompanyDto dto) {
//        if (authRepository.existsByUsername(dto.getUsername())) {
//            throw new AuthManagerException(ErrorType.USERNAME_ALREADY_EXIST);
//        }
//        Auth auth;
//        if (dto.getTaxNumber().isEmpty() || dto.getCompanyName().isEmpty()) {
//            auth = IAuthMapper.INSTANCE.toRegisterCompany(dto);
//            auth.setActivationCode(CodeGenerator.generateCode());
//            save(auth);
//            authUserProducer.createUser(AuthUserModel.builder().authId(auth.getId()).phone(dto.getPhone()).
//                    address(dto.getAddress()).email(dto.getEmail()).role(auth.getRole()).firstName(dto.getFirstName()).
//                    lastName(dto.getLastName()).username(dto.getUsername()).role(auth.getRole()).build());
//            RegisterResponseVisitorDto responseVisitorDto = IAuthMapper.INSTANCE.toRegisterResponseDto(auth);
//            String token = jwtTokenManager.createToken(auth.getId())
//                    .orElseThrow(() -> new AuthManagerException(ErrorType.INVALID_TOKEN));
//            responseVisitorDto.setToken(token);
//            responseVisitorDto.setComment("Tax number ve company name girmediğiniz için kullanıcı kaydınız başarı ile gerçekleşti.Active etmek için mailinizi kontrol ediniz");
//            return responseVisitorDto;
//        }else {//Company e gönderilecek yer
//            auth = IAuthMapper.INSTANCE.toRegisterCompany(dto);
//            auth.setRole(ERole.COMPANY_OWNER);
//            save(auth);
//            authUserProducer.createUser(AuthUserModel.builder().authId(auth.getId()).phone(dto.getPhone()).
//                    address(dto.getAddress()).email(dto.getEmail()).firstName(dto.getFirstName()).
//                    lastName(dto.getLastName()).username(dto.getUsername()).role(ERole.COMPANY_OWNER).build());
//            authCompanyProducer.authCompany(AuthCompanyModel.builder().authId(auth.getId()).companyPhoneNumber(dto.getPhone()).
//                    companyAddress(dto.getAddress()).taxNumber(dto.getTaxNumber()).companyName(dto.getCompanyName()).build());
//            RegisterResponseVisitorDto responseVisitorDto = IAuthMapper.INSTANCE.toRegisterResponseDto(auth);
//            String token = jwtTokenManager.createToken(auth.getId())
//                    .orElseThrow(() -> new AuthManagerException(ErrorType.INVALID_TOKEN));
//            responseVisitorDto.setToken(token);
//            responseVisitorDto.setComment("Company kaydınız ile gerçekleşti.Company onayınızı admin yapacaktık.");
//            return responseVisitorDto;
//
//        }
//    }

    public String logout(LogoutRequestDto dto) {
        Optional<Long> idOptional = jwtTokenManager.getIdFromToken(dto.getToken());

        if (idOptional.isPresent()) {
            Long id = idOptional.get();

            Optional<Auth> optionalAuth = authRepository.findOptionalById(id);

            if (optionalAuth.isPresent()) {
                Auth auth = optionalAuth.get();

                if (auth.getLogged()) {
                    auth.setLogged(false);
                    save(auth);

                    return "Başarıyla çıkış yapıldı.";
                } else {
                    throw new AuthManagerException(ErrorType.ALREADY_LOGGED_OUT);
                }
            } else {
                throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
            }
        } else {
            throw new AuthManagerException(ErrorType.INVALID_TOKEN);
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

    @Transactional
    public String activateCompanyStatus(String token) {

        System.out.println(token);
        System.out.println(token.toString());


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
        return "Şirket Onayı Başarıyla Tamamlanmıştır.";
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
        authWorkerProducer.authWorker(AuthWorkerModel.builder().authId(auth.getId()).build());
    }

    public Auth getAuthByUsername(String username) {
        Optional<Auth> auth = authRepository.findAuthByUsername(username);
        return auth.orElse(null);
    }


    public Optional<String> tokenLazim(Long id, ERole role) {
        return jwtTokenManager.createToken(id, role);
    }
}