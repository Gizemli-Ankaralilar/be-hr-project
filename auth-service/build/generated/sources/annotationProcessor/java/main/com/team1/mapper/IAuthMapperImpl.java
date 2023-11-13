package com.team1.mapper;

import com.team1.dto.request.RegisterRequestCompanyDto;
import com.team1.dto.request.RegisterRequestVisitorDto;
import com.team1.dto.request.SendMailRequestDto;
import com.team1.dto.response.RegisterResponseVisitorDto;
import com.team1.rabbitmq.model.CompanyWorkerAuthModel;
import com.team1.repository.entity.Auth;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-12T00:12:47+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.jar, environment: Java 17.0.8.1 (Amazon.com Inc.)"
)
@Component
public class IAuthMapperImpl implements IAuthMapper {

    @Override
    public Auth toRegisterAuth(RegisterRequestVisitorDto dto) {
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
    public Auth toRegisterCompany(RegisterRequestCompanyDto dto) {
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
    public Auth toRegisterCompany(CompanyWorkerAuthModel model) {
        if ( model == null ) {
            return null;
        }

        Auth.AuthBuilder<?, ?> auth = Auth.builder();

        auth.username( model.getUsername() );
        auth.password( model.getPassword() );
        auth.email( model.getEmail() );
        auth.role( model.getRole() );
        auth.companyId( model.getCompanyId() );

        return auth.build();
    }

    @Override
    public RegisterResponseVisitorDto toRegisterResponseDto(Auth auth) {
        if ( auth == null ) {
            return null;
        }

        RegisterResponseVisitorDto.RegisterResponseVisitorDtoBuilder registerResponseVisitorDto = RegisterResponseVisitorDto.builder();

        registerResponseVisitorDto.id( auth.getId() );
        registerResponseVisitorDto.username( auth.getUsername() );
        registerResponseVisitorDto.password( auth.getPassword() );

        return registerResponseVisitorDto.build();
    }

    @Override
    public SendMailRequestDto toSendMailRequestDto(Auth auth) {
        if ( auth == null ) {
            return null;
        }

        SendMailRequestDto.SendMailRequestDtoBuilder sendMailRequestDto = SendMailRequestDto.builder();

        sendMailRequestDto.authId( auth.getId() );
        sendMailRequestDto.username( auth.getUsername() );
        sendMailRequestDto.email( auth.getEmail() );

        return sendMailRequestDto.build();
    }

    @Override
    public RegisterRequestVisitorDto fromRequestCompanyDtoToRequestVisitorDto(RegisterRequestCompanyDto dto) {
        if ( dto == null ) {
            return null;
        }

        RegisterRequestVisitorDto.RegisterRequestVisitorDtoBuilder registerRequestVisitorDto = RegisterRequestVisitorDto.builder();

        registerRequestVisitorDto.username( dto.getUsername() );
        registerRequestVisitorDto.email( dto.getEmail() );
        registerRequestVisitorDto.password( dto.getPassword() );
        registerRequestVisitorDto.firstName( dto.getFirstName() );
        registerRequestVisitorDto.lastName( dto.getLastName() );
        registerRequestVisitorDto.phone( dto.getPhone() );
        registerRequestVisitorDto.address( dto.getAddress() );

        return registerRequestVisitorDto.build();
    }
}
