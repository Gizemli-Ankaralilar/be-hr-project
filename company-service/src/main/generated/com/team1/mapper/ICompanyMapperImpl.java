package com.team1.mapper;

import com.team1.dto.request.RegisterRequestVisitorDto;
import com.team1.dto.request.RegisterSaveCompanyDto;
import com.team1.dto.request.SaveCompanyDto;
import com.team1.dto.response.RegisterResponseCompanyDto;
import com.team1.repository.entity.Company;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-30T00:21:13+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class ICompanyMapperImpl implements ICompanyMapper {

    @Override
    public RegisterRequestVisitorDto toRequestVisitorDto(Company company) {
        if ( company == null ) {
            return null;
        }

        RegisterRequestVisitorDto.RegisterRequestVisitorDtoBuilder registerRequestVisitorDto = RegisterRequestVisitorDto.builder();

        registerRequestVisitorDto.username( company.getUsername() );
        registerRequestVisitorDto.email( company.getEmail() );
        registerRequestVisitorDto.password( company.getPassword() );

        return registerRequestVisitorDto.build();
    }

    @Override
    public Company toCompany(SaveCompanyDto dto) {
        if ( dto == null ) {
            return null;
        }

        Company.CompanyBuilder<?, ?> company = Company.builder();

        company.username( dto.getUsername() );
        company.password( dto.getPassword() );
        company.companyName( dto.getCompanyName() );
        company.email( dto.getEmail() );
        company.taxNumber( dto.getTaxNumber() );

        return company.build();
    }

    @Override
    public RegisterSaveCompanyDto toSaveCompany(Company company) {
        if ( company == null ) {
            return null;
        }

        RegisterSaveCompanyDto.RegisterSaveCompanyDtoBuilder registerSaveCompanyDto = RegisterSaveCompanyDto.builder();

        registerSaveCompanyDto.companyId( company.getId() );
        registerSaveCompanyDto.username( company.getUsername() );
        registerSaveCompanyDto.email( company.getEmail() );
        registerSaveCompanyDto.password( company.getPassword() );
        registerSaveCompanyDto.companyName( company.getCompanyName() );
        registerSaveCompanyDto.taxNumber( company.getTaxNumber() );

        return registerSaveCompanyDto.build();
    }

    @Override
    public RegisterResponseCompanyDto toRegisterResponseDto(Company company) {
        if ( company == null ) {
            return null;
        }

        RegisterResponseCompanyDto registerResponseCompanyDto = new RegisterResponseCompanyDto();

        return registerResponseCompanyDto;
    }
}
