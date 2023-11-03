package com.team1.service;


import com.team1.dto.request.SaveUserRequestDto;
import com.team1.exception.ErrorType;
import com.team1.exception.UserManagerException;
import com.team1.mapper.IUserMapper;
import com.team1.repository.IUserRepository;
import com.team1.repository.entity.UserProfile;
import com.team1.utility.JwtTokenManager;
import com.team1.utility.ServiceManager;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
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

    public Boolean saveUser(SaveUserRequestDto dto) {
        try {
            UserProfile userProfile = IUserMapper.INSTANCE.toUserProfile(dto);
            save(userProfile);
            return true;
        }catch (Exception e) {
            throw new UserManagerException(ErrorType.BAD_REQUEST);
        }
    }

    public UserProfile getUserInformation(Long userId) {
        Optional<UserProfile> userProfileInformation = userRepository.findById(userId);
        return userProfileInformation.orElse(null);
    }

}
