package com.team1.mapper;

import com.team1.dto.response.ResponceIncomeDto;
import com.team1.dto.response.ResponseSpendingDto;
import com.team1.rabbitmq.model.AuthCompanyModel;
import com.team1.repository.entity.Company;
import com.team1.repository.entity.Finance;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-12T00:16:47+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.jar, environment: Java 17.0.8.1 (Amazon.com Inc.)"
)
@Component
public class ICompanyMapperImpl implements ICompanyMapper {

    @Override
    public Company toAuthCompany(AuthCompanyModel model) {
        if ( model == null ) {
            return null;
        }

        Company.CompanyBuilder<?, ?> company = Company.builder();

        company.authId( model.getAuthId() );
        company.companyName( model.getCompanyName() );
        company.taxNumber( model.getTaxNumber() );
        company.companyAddress( model.getCompanyAddress() );
        company.companyPhoneNumber( model.getCompanyPhoneNumber() );

        return company.build();
    }

    @Override
    public ResponseSpendingDto toSpendingFinance(Finance finance) {
        if ( finance == null ) {
            return null;
        }

        ResponseSpendingDto.ResponseSpendingDtoBuilder responseSpendingDto = ResponseSpendingDto.builder();

        responseSpendingDto.spending( finance.getSpending() );
        responseSpendingDto.spendingAmount( finance.getSpendingAmount() );

        return responseSpendingDto.build();
    }

    @Override
    public ResponceIncomeDto toIncomeFinance(Finance finance) {
        if ( finance == null ) {
            return null;
        }

        ResponceIncomeDto.ResponceIncomeDtoBuilder responceIncomeDto = ResponceIncomeDto.builder();

        responceIncomeDto.income( finance.getIncome() );
        if ( finance.getIncomeAmount() != null ) {
            responceIncomeDto.incomeAmount( String.valueOf( finance.getIncomeAmount() ) );
        }

        return responceIncomeDto.build();
    }
}
