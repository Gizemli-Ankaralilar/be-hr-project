package com.team1.mapper;

import com.team1.dto.request.RegisterRequestUserDto;
import com.team1.dto.request.RegisterRequestVisitorDto;
import com.team1.dto.request.RegisterSaveCompanyDto;
import com.team1.dto.response.RegisterResponseVisitorDto;
import com.team1.rabbitmq.model.CreateAuthModel;
import com.team1.repository.entity.Auth;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-31T22:14:31+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
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

    @Override
    public RegisterRequestUserDto toUserSaveRequestDto(Auth auth) {
        if ( auth == null ) {
            return null;
        }

        RegisterRequestUserDto.RegisterRequestUserDtoBuilder registerRequestUserDto = RegisterRequestUserDto.builder();

        registerRequestUserDto.authId( auth.getId() );
        registerRequestUserDto.username( auth.getUsername() );
        registerRequestUserDto.email( auth.getEmail() );
        registerRequestUserDto.password( auth.getPassword() );

        return registerRequestUserDto.build();
    }

    @Override
    public Auth toAuthCompany(RegisterSaveCompanyDto dto) {
        if ( dto == null ) {
            return null;
        }

        Auth.AuthBuilder<?, ?> auth = Auth.builder();

        auth.username( dto.getUsername() );
        auth.password( dto.getPassword() );
        auth.email( dto.getEmail() );
        auth.companyId( dto.getCompanyId() );

        return auth.build();
    }

    @Override
    public Auth toSaveAutRabbit(CreateAuthModel model) {
        if ( model == null ) {
            return null;
        }

        Auth.AuthBuilder<?, ?> auth = Auth.builder();

        auth.username( model.getUsername() );
        auth.password( model.getPassword() );
        auth.email( model.getEmail() );

        return auth.build();
    }
}
