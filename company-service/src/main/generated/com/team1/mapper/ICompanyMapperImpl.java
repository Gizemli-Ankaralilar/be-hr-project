package com.team1.mapper;

import com.team1.dto.request.RegisterRequestCompanyDto;
import com.team1.dto.request.RegisterRequestVisitorDto;
import com.team1.dto.response.RegisterResponseCompanyDto;
import com.team1.repository.entity.Company;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-27T13:30:36+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8.1 (Amazon.com Inc.)"
)
@Component
public class ICompanyMapperImpl implements ICompanyMapper {

    @Override
    public Company toCompany(RegisterRequestCompanyDto dto) {
        if ( dto == null ) {
            return null;
        }

        Company.CompanyBuilder<?, ?> company = Company.builder();

        company.username( dto.getUsername() );
        company.password( dto.getPassword() );
        company.companyName( dto.getCompanyName() );
        company.taxNumber( dto.getTaxNumber() );
        company.companyEmail( dto.getCompanyEmail() );
        company.companyAddress( dto.getCompanyAddress() );
        company.companyPhoneNumber( dto.getCompanyPhoneNumber() );

        return company.build();
    }

    @Override
    public RegisterResponseCompanyDto toRegisterResponseDto(Company company) {
        if ( company == null ) {
            return null;
        }

        RegisterResponseCompanyDto registerResponseCompanyDto = new RegisterResponseCompanyDto();

        return registerResponseCompanyDto;
    }

    @Override
    public RegisterRequestVisitorDto toRequestVisitorDto(Company company) {
        if ( company == null ) {
            return null;
        }

        RegisterRequestVisitorDto.RegisterRequestVisitorDtoBuilder registerRequestVisitorDto = RegisterRequestVisitorDto.builder();

        registerRequestVisitorDto.username( company.getUsername() );
        registerRequestVisitorDto.password( company.getPassword() );

        return registerRequestVisitorDto.build();
    }
}