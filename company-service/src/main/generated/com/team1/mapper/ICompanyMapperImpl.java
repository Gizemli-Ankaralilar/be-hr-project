package com.team1.mapper;

import com.team1.dto.request.SpendingDto;
import com.team1.dto.response.ResponseSpendingDto;
import com.team1.rabbitmq.model.AuthCompanyModel;
import com.team1.repository.entity.Company;
import com.team1.repository.entity.Finance;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-08T20:28:21+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
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
    public Finance toFinance(SpendingDto dto) {
        if ( dto == null ) {
            return null;
        }

        Finance.FinanceBuilder<?, ?> finance = Finance.builder();

        finance.spending( dto.getSpending() );
        finance.income( dto.getIncome() );
        finance.spendingAmount( dto.getSpendingAmount() );
        finance.incomeAmount( dto.getIncomeAmount() );

        return finance.build();
    }

    @Override
    public ResponseSpendingDto toResponceFinance(Finance finance) {
        if ( finance == null ) {
            return null;
        }

        ResponseSpendingDto.ResponseAmountDtoBuilder responseAmountDto = ResponseSpendingDto.builder();

        responseAmountDto.spending( finance.getSpending() );
        responseAmountDto.income( finance.getIncome() );
        responseAmountDto.spendingAmount( finance.getSpendingAmount() );
        responseAmountDto.incomeAmount( finance.getIncomeAmount() );

        return responseAmountDto.build();
    }
}
