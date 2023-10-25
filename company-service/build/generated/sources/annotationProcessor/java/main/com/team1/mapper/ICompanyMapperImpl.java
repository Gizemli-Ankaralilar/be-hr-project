package com.team1.mapper;

import com.team1.dto.request.RegisterRequestCompanyDto;
import com.team1.dto.response.RegisterResponseCompanyDto;
import com.team1.repository.entity.Company;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-25T12:52:51+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.jar, environment: Java 17.0.8 (Oracle Corporation)"
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
}
