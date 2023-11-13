package com.team1.mapper;

import com.team1.dto.request.SendMailRequestDto;
import com.team1.repository.entity.MailProfile;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-11T22:53:13+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8.1 (Amazon.com Inc.)"
)
@Component
public class IMailMapperImpl implements IMailMapper {

    @Override
    public MailProfile toUserProfile(SendMailRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        MailProfile.MailProfileBuilder<?, ?> mailProfile = MailProfile.builder();

        mailProfile.authId( dto.getAuthId() );
        mailProfile.username( dto.getUsername() );
        mailProfile.email( dto.getEmail() );

        return mailProfile.build();
    }
}
