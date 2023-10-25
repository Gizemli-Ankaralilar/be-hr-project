package com.team1.mapper;

import com.team1.dto.request.RegisterRequestCompanyDto;
import com.team1.dto.request.RegisterRequestVisitorDto;
import com.team1.dto.response.RegisterResponseVisitorDto;
import com.team1.repository.entity.Auth;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-25T12:49:14+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.jar, environment: Java 17.0.8 (Oracle Corporation)"
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
    public Auth toAuth(RegisterRequestCompanyDto dto) {
        if ( dto == null ) {
            return null;
        }

        Auth.AuthBuilder<?, ?> auth = Auth.builder();

        auth.username( dto.getUsername() );
        auth.password( dto.getPassword() );

        return auth.build();
    }

    @Override
    public RegisterRequestCompanyDto toCompanyRegisterDto(Auth auth) {
        if ( auth == null ) {
            return null;
        }

        RegisterRequestCompanyDto.RegisterRequestCompanyDtoBuilder registerRequestCompanyDto = RegisterRequestCompanyDto.builder();

        registerRequestCompanyDto.authId( auth.getId() );
        registerRequestCompanyDto.username( auth.getUsername() );
        registerRequestCompanyDto.password( auth.getPassword() );

        return registerRequestCompanyDto.build();
    }
}
