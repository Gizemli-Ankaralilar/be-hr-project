package com.team1.mapper;

import com.team1.rabbitmq.model.AuthUserModel;
import com.team1.repository.entity.UserProfile;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
<<<<<<< HEAD
    date = "2023-11-08T19:44:17+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
=======
<<<<<<< HEAD
    date = "2023-11-08T12:32:57+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8.1 (Amazon.com Inc.)"
=======
    date = "2023-11-08T21:18:31+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Amazon.com Inc.)"
>>>>>>> c6b5c687caefc555ff8ca77d26c5d2cae6b7d703
>>>>>>> 85bf08a2f0b3a47d210763eed1a748bcd9382b91
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
