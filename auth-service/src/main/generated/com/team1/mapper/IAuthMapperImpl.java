package com.team1.mapper;

import com.team1.dto.request.RegisterRequestVisitorDto;
import com.team1.dto.response.RegisterResponseVisitorDto;
import com.team1.rabbitmq.model.MailModel;
import com.team1.repository.entity.Auth;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
<<<<<<< HEAD
    date = "2023-10-26T16:16:50+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8.1 (Amazon.com Inc.)"
=======
    date = "2023-10-26T16:37:10+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
>>>>>>> master
)
@Component
public class IAuthMapperImpl implements IAuthMapper {

    @Override
    public Auth toAuth(RegisterRequestVisitorDto dto) {
        if ( dto == null ) {
            return null;
        }

        Auth.AuthBuilder<?, ?> auth = Auth.builder();

        auth.username( dto.getUsername() );
        auth.password( dto.getPassword() );
        auth.email( dto.getEmail() );

        return auth.build();
    }

    @Override
    public RegisterResponseVisitorDto toRegisterResponseDto(Auth auth) {
        if ( auth == null ) {
            return null;
        }

        RegisterResponseVisitorDto.RegisterResponseVisitorDtoBuilder registerResponseVisitorDto = RegisterResponseVisitorDto.builder();

        registerResponseVisitorDto.id( auth.getId() );
        registerResponseVisitorDto.activationCode( auth.getActivationCode() );
        registerResponseVisitorDto.username( auth.getUsername() );

        return registerResponseVisitorDto.build();
    }
<<<<<<< HEAD

    @Override
    public SaveUserRequestDto toSaveUserRequestDto(Auth auth) {
        if ( auth == null ) {
            return null;
        }

        SaveUserRequestDto.SaveUserRequestDtoBuilder saveUserRequestDto = SaveUserRequestDto.builder();

        saveUserRequestDto.authId( auth.getId() );
        saveUserRequestDto.username( auth.getUsername() );
        saveUserRequestDto.email( auth.getEmail() );

        return saveUserRequestDto.build();
    }

    @Override
    public MailModel toMailModel(Auth auth) {
        if ( auth == null ) {
            return null;
        }

        MailModel.MailModelBuilder mailModel = MailModel.builder();

        mailModel.activationCode( auth.getActivationCode() );
        mailModel.email( auth.getEmail() );
        mailModel.username( auth.getUsername() );

        return mailModel.build();
    }
=======
>>>>>>> master
}
