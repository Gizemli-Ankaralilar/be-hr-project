package com.team1.service;


import com.team1.dto.request.SaveUserRequestDto;
import com.team1.exception.ErrorType;
import com.team1.exception.UserManagerException;
import com.team1.mapper.IUserMapper;
import com.team1.rabbitmq.model.SaveAuthModel;
import com.team1.rabbitmq.model.SaveCompanyUserModel;
import com.team1.rabbitmq.producer.UserWorkerProducer;
import com.team1.repository.IUserRepository;
import com.team1.repository.entity.UserProfile;
import com.team1.utility.JwtTokenManager;
import com.team1.utility.ServiceManager;
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
    private final IUserMapper userMapper;
    private final UserWorkerProducer userWorkerProducer;


    public UserService(IUserRepository userRepository, JwtTokenManager jwtTokenManager, IUserMapper userMapper, UserWorkerProducer userWorkerProducer) {
        super(userRepository);
        this.userRepository = userRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.userMapper = userMapper;
        this.userWorkerProducer = userWorkerProducer;
    }

    public Boolean saveUser(SaveUserRequestDto dto) {
//        try {
//            UserProfile userProfile = IUserMapper.INSTANCE.toUserProfile(dto);
//            save(userProfile);
//            return true;
//        }catch (Exception e) {
//            throw new UserManagerException(ErrorType.BAD_REQUEST);
//        }
        return true;
    }

    public UserProfile getUserInformation(Long userId) {
        Optional<UserProfile> userProfileInformation = userRepository.findById(userId);
        return userProfileInformation.orElse(null);
    }

    public void saveRabbit(SaveAuthModel model) {
        UserProfile userProfile = IUserMapper.INSTANCE.toSaveUserRabbit(model);
        save(userProfile);
        //burada worker bilgileri worker'a gönderilcek.Aslında Auth a kayıt edilmiş bir bilgi gönderilecek
    }

    public void saveCompanyRabbit(SaveCompanyUserModel model) {
        UserProfile userProfile = IUserMapper.INSTANCE.toSaveCompanyUserRabbit(model);
        save(userProfile);
    }

    public Optional<UserProfile> findByCompanyId(Long authId) {
        Optional<UserProfile> userProfile = userRepository.findById(authId);
        if (userProfile.isEmpty()) {
            throw new UserManagerException(ErrorType.BAD_REQUEST);
        }
        return userProfile;
    }


    public void saveWorkerAuth(SaveCompanyUserModel model) {
        UserProfile userProfile = UserProfile.builder().authId(model.getAuthId()).password(model.getPassword()).
        address(model.getAddress()).email(model.getEmail()).username(model.getUsername()).companyId(model.getCompanyId()).
        lastName(model.getLastName()).surName(model.getSurName()).phone(model.getPhone()).build();
        save(userProfile);
    }
}
