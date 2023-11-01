package com.team1.service;

import com.team1.dto.request.*;
import com.team1.dto.response.RegisterResponseVisitorDto;
import com.team1.exception.AuthManagerException;
import com.team1.exception.ErrorType;
import com.team1.manager.IUserProfileManager;
import com.team1.mapper.IAuthMapper;
import com.team1.rabbitmq.model.CreateAuthModel;
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
    private final IUserProfileManager iUserProfileManager;


    public AuthService(IAuthRepository authRepository, JwtTokenManager jwtTokenManager, IUserProfileManager iUserProfileManager) {
        super(authRepository);
        this.authRepository = authRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.iUserProfileManager = iUserProfileManager;
    }

    @Transactional
    public RegisterResponseVisitorDto register(RegisterRequestVisitorDto dto) {
        Auth auth = IAuthMapper.INSTANCE.toAuth(dto);
        auth.setActivationCode(CodeGenerator.generateCode());

        if (authRepository.existsByUsername(dto.getUsername())) {
            throw new AuthManagerException(ErrorType.USERNAME_ALREADY_EXIST);
        }
            save(auth);

        //TODO  User mapper kullan
/*        iUserProfileManager.save(RegisterRequestUserDto.builder()
                .authId(auth.getId())
                .username(auth.getUsername())
                .email(auth.getEmail()).build());*/

        iUserProfileManager.save(IAuthMapper.INSTANCE.toUserSaveRequestDto(auth));

        RegisterResponseVisitorDto responseVisitorDto = IAuthMapper.INSTANCE.toRegisterResponseDto(auth);
        String token = jwtTokenManager.createToken(auth.getId())
                .orElseThrow(() -> new AuthManagerException(ErrorType.INVALID_TOKEN));
        responseVisitorDto.setToken(token);


        return responseVisitorDto;
    }

    public Boolean companySave(RegisterSaveCompanyDto dto) {
        try {
            Auth auth = IAuthMapper.INSTANCE.toAuthCompany(dto);
            save(auth);
            iUserProfileManager.save(IAuthMapper.INSTANCE.toUserSaveRequestDto(auth));
            return true;
        } catch (Exception e) {
            throw new AuthManagerException(ErrorType.BAD_REQUEST);
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
        if(!optionalAuth.get().getActivated()==true){
            optionalAuth.get().setActivated(true);
        } else {
            throw new AuthManagerException(ErrorType.ALREADY_ACTIVE);
        }
        if(optionalAuth.get().getStatus().equals(ERole.ADMIN)) {
            //admin sayfası openfeign
        } else if (optionalAuth.get().getStatus().equals(ERole.COMPANY_OWNER)) {
            //company sayfasına yönlendirme yapılacak
        } else if (optionalAuth.get().getStatus().equals(ERole.WORKER)) {
            //worker sayfasına yönlendirilecek
        } else {
            //Anasayfaya yönlendirilecek
        }
        return jwtTokenManager.createTokenCompany(optionalAuth.get().getId(), optionalAuth.get().getCompanyId())
                .orElseThrow(() -> new AuthManagerException(ErrorType.TOKEN_NOT_CREATED));
    }

    @Transactional
    public String activateStatus(ActivateRequestDto dto) {
        Optional<Long> id = jwtTokenManager.getIdFromToken(dto.getToken());
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
        if (dto.getActivationCode().equals(optionalAuth.get().getActivationCode())) {
            optionalAuth.get().setStatus(EStatus.ACTIVE);
            update(optionalAuth.get());
            // userManager.activateStatus(dto.getToken()); // open feign ile haberleşme
            //activationProducer.activateStatus(dto.getToken()); // rabbitmq ile haberleşme
            return "Hesabınız aktive edilmiştir";
        } else {
            throw new AuthManagerException(ErrorType.INVALID_CODE);
        }
    }


    public Boolean createAuthRabbit(CreateAuthModel model) {
        try {
            Auth auth = IAuthMapper.INSTANCE.toSaveAutRabbit(model);
            save(auth);
            return true;
        }catch (Exception e){
            throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
        }
    }
}