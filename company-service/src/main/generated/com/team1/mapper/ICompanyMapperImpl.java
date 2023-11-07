package com.team1.mapper;

import com.team1.rabbitmq.model.AuthCompanyModel;
import com.team1.repository.entity.Company;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-07T13:05:34+0300",
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

        return company.build();
    }
}
