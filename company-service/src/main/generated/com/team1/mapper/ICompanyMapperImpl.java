package com.team1.mapper;

import com.team1.rabbitmq.model.SaveCompanyModel;
import com.team1.repository.entity.Company;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-03T23:44:07+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class ICompanyMapperImpl implements ICompanyMapper {

    @Override
    public Company toSaveCompanyRabbit(SaveCompanyModel model) {
        if ( model == null ) {
            return null;
        }

        Company.CompanyBuilder<?, ?> company = Company.builder();

        if ( model.getAuthId() != null ) {
            company.authId( String.valueOf( model.getAuthId() ) );
        }
        company.companyName( model.getCompanyName() );
        company.username( model.getUsername() );
        company.email( model.getEmail() );
        company.password( model.getPassword() );
        company.taxNumber( model.getTaxNumber() );

        return company.build();
    }
}
