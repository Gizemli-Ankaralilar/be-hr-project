package com.team1.service;

import com.team1.dto.request.ActivateRequestDto;
import com.team1.dto.request.LoginRequestDto;
import com.team1.dto.request.RegisterRequestVisitorDto;
import com.team1.dto.request.SaveUserRequestDto;
import com.team1.dto.response.RegisterResponseVisitorDto;
import com.team1.exception.AuthManagerException;
import com.team1.exception.ErrorType;
import com.team1.manager.IAuthUserManager;
import com.team1.mapper.IAuthMapper;
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

    private final IAuthUserManager authUserManager;



    public AuthService(IAuthRepository authRepository, JwtTokenManager jwtTokenManager, IAuthUserManager authUserManager) {
        super(authRepository);
        this.authRepository = authRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.authUserManager = authUserManager;
    }

    @Transactional
    public RegisterResponseVisitorDto register(RegisterRequestVisitorDto dto) {
        Auth auth = IAuthMapper.INSTANCE.toAuth(dto);
        auth.setActivationCode(CodeGenerator.generateCode());
        if (authRepository.existsByUsername(dto.getUsername())) {
            throw new AuthManagerException(ErrorType.USERNAME_ALREADY_EXIST);
        }
        save(auth);

        authUserManager.save(SaveUserRequestDto.builder().authId(auth.getId()).username(auth.getUsername()).email(auth.getEmail()).build());
        //authUserManager.save(IAuthMapper.INSTANCE.toSaveUserRequestDto(auth));


        RegisterResponseVisitorDto responseVisitorDto = IAuthMapper.INSTANCE.toRegisterResponseDto(auth);
        String token = jwtTokenManager.createToken(auth.getId()).orElseThrow(() -> new AuthManagerException(ErrorType.INVALID_TOKEN));
        responseVisitorDto.setToken(token);
        return responseVisitorDto;
    }

    @Transactional
    public String login(LoginRequestDto dto) {
        Optional<Auth> optionalAuth = authRepository.findOptionalByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (optionalAuth.isEmpty()) {
            throw new AuthManagerException(ErrorType.LOGIN_ERROR);
        }
        if (!optionalAuth.get().getStatus().equals(EStatus.ACTIVE)) {
            throw new AuthManagerException(ErrorType.ACCOUNT_NOT_ACTIVE);
        }

        return jwtTokenManager.createToken(optionalAuth.get().getId(), optionalAuth.get().getRole())
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

}