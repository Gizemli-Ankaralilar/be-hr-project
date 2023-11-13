package com.team1.mapper;

import com.team1.rabbitmq.model.AuthUserModel;
import com.team1.repository.entity.UserProfile;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-11T22:53:26+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8.1 (Amazon.com Inc.)"
)
@Component
public class IUserMapperImpl implements IUserMapper {

    @Override
    public UserProfile authtouser(AuthUserModel model) {
        if ( model == null ) {
            return null;
        }

        UserProfile.UserProfileBuilder<?, ?> userProfile = UserProfile.builder();

        userProfile.authId( model.getAuthId() );
        userProfile.companyId( model.getCompanyId() );
        userProfile.username( model.getUsername() );
        userProfile.lastName( model.getLastName() );
        userProfile.firstName( model.getFirstName() );
        userProfile.email( model.getEmail() );
        userProfile.phone( model.getPhone() );
        userProfile.address( model.getAddress() );
        userProfile.role( model.getRole() );

        return userProfile.build();
    }
}
