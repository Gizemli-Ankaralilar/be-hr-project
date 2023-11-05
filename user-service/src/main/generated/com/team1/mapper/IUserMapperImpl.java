package com.team1.mapper;

import com.team1.rabbitmq.model.SaveAuthModel;
import com.team1.rabbitmq.model.SaveCompanyUserModel;
import com.team1.repository.entity.UserProfile;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-05T17:02:42+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class IUserMapperImpl implements IUserMapper {

    @Override
    public UserProfile toSaveUserRabbit(SaveAuthModel model) {
        if ( model == null ) {
            return null;
        }

        UserProfile.UserProfileBuilder<?, ?> userProfile = UserProfile.builder();

        userProfile.authId( model.getAuthId() );
        userProfile.username( model.getUsername() );
        userProfile.lastName( model.getLastName() );
        userProfile.surName( model.getSurName() );
        userProfile.email( model.getEmail() );
        userProfile.phone( model.getPhone() );
        userProfile.password( model.getPassword() );
        userProfile.address( model.getAddress() );

        return userProfile.build();
    }

    @Override
    public UserProfile toSaveCompanyUserRabbit(SaveCompanyUserModel model) {
        if ( model == null ) {
            return null;
        }

        UserProfile.UserProfileBuilder<?, ?> userProfile = UserProfile.builder();

        userProfile.authId( model.getAuthId() );
        userProfile.companyId( model.getCompanyId() );

        return userProfile.build();
    }
}
