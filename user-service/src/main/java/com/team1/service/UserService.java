package com.team1.service;


import com.team1.repository.IUserRepository;
import com.team1.repository.entity.UserProfile;
import com.team1.utility.JwtTokenManager;
import com.team1.utility.ServiceManager;
import org.springframework.stereotype.Service;
/*
    update metodu yazalım
    update metodu userporfile bilgilerimi guncelleyecek aynı zamanda
    update metodunda username veya email 2 sinden biri değişmiş ise
    auth servisdede guncelleme yapsın
 */

@Service
public class UserService extends ServiceManager<UserProfile, Long> {
    private final IUserRepository userRepository;
    private final JwtTokenManager jwtTokenManager;


    public UserService(IUserRepository userRepository, JwtTokenManager jwtTokenManager) {
        super(userRepository);
        this.userRepository = userRepository;
        this.jwtTokenManager = jwtTokenManager;
    }
}
