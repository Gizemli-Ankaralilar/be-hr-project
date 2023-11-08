package com.team1.mapper;

import com.team1.rabbitmq.model.AuthCompanyModel;
import com.team1.repository.entity.Company;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
<<<<<<< HEAD
    date = "2023-11-08T12:32:36+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8.1 (Amazon.com Inc.)"
=======
    date = "2023-11-08T21:18:15+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Amazon.com Inc.)"
>>>>>>> c6b5c687caefc555ff8ca77d26c5d2cae6b7d703
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
}
