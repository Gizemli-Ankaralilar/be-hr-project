package com.team1.mapper;

import com.team1.dto.request.SaveUserRequestDto;
import com.team1.repository.entity.UserProfile;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-29T23:18:14+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class IUserMapperImpl implements IUserMapper {

    @Override
    public UserProfile toUserProfile(SaveUserRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserProfile.UserProfileBuilder<?, ?> userProfile = UserProfile.builder();

        userProfile.authId( dto.getAuthId() );
        userProfile.username( dto.getUsername() );
        userProfile.email( dto.getEmail() );

        return userProfile.build();
    }
}
