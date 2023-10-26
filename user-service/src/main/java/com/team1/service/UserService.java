package com.team1.service;

import com.team1.repository.IAuthRepository;
import com.team1.repository.entity.User;
import com.team1.utility.JwtTokenManager;
import com.team1.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceManager<User, Long> {

    private final IAuthRepository authRepository;
    private final JwtTokenManager jwtTokenManager;


    public UserService(IAuthRepository authRepository, JwtTokenManager jwtTokenManager) {
        super(authRepository);
        this.authRepository = authRepository;
        this.jwtTokenManager = jwtTokenManager;
    }
}