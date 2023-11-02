package com.team1.mapper;

import com.team1.dto.request.RegisterRequestVisitorDto;
import com.team1.dto.request.SaveCompanyDto;
import com.team1.dto.request.SaveWorkerDto;
import com.team1.dto.response.RegisterResponseCompanyDto;
import com.team1.rabbitmq.model.CreateAuthModel;
import com.team1.rabbitmq.model.CreateCompanyAuthModel;
import com.team1.rabbitmq.model.CreateWorkerAuthModel;
import com.team1.rabbitmq.model.QueryAuthIdModel;
import com.team1.repository.entity.Company;
import com.team1.repository.entity.Worker;
import com.team1.repository.enums.ERole;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-02T19:31:50+0300",
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

        company.companyName( dto.getCompanyName() );
        company.username( dto.getUsername() );
        company.email( dto.getEmail() );
        company.password( dto.getPassword() );
        company.taxNumber( dto.getTaxNumber() );

        return company.build();
    }

    @Override
    public CreateCompanyAuthModel toSaveCompanyRabbit(Company company) {
        if ( company == null ) {
            return null;
        }

        CreateCompanyAuthModel.CreateCompanyAuthModelBuilder createCompanyAuthModel = CreateCompanyAuthModel.builder();

        createCompanyAuthModel.companyId( company.getId() );
        createCompanyAuthModel.username( company.getUsername() );
        createCompanyAuthModel.password( company.getPassword() );
        createCompanyAuthModel.email( company.getEmail() );

        return createCompanyAuthModel.build();
    }

    @Override
    public CreateAuthModel toSaveAutRabbit(Company company) {
        if ( company == null ) {
            return null;
        }

        CreateAuthModel.CreateAuthModelBuilder createAuthModel = CreateAuthModel.builder();

        createAuthModel.username( company.getUsername() );
        createAuthModel.password( company.getPassword() );
        createAuthModel.email( company.getEmail() );

        return createAuthModel.build();
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
    public Worker toSaveWorker(SaveWorkerDto dto) {
        if ( dto == null ) {
            return null;
        }

        Worker.WorkerBuilder<?, ?> worker = Worker.builder();

        worker.username( dto.getUsername() );
        worker.email( dto.getEmail() );
        worker.password( dto.getPassword() );
        if ( dto.getRole() != null ) {
            worker.role( Enum.valueOf( ERole.class, dto.getRole() ) );
        }

        return worker.build();
    }

    @Override
    public CreateWorkerAuthModel toSaveWorkerAuth(Worker worker) {
        if ( worker == null ) {
            return null;
        }

        CreateWorkerAuthModel.CreateWorkerAuthModelBuilder createWorkerAuthModel = CreateWorkerAuthModel.builder();

        createWorkerAuthModel.username( worker.getUsername() );
        createWorkerAuthModel.password( worker.getPassword() );
        createWorkerAuthModel.email( worker.getEmail() );

        return createWorkerAuthModel.build();
    }

    @Override
    public QueryAuthIdModel toWorkerIdAuth(Worker worker) {
        if ( worker == null ) {
            return null;
        }

        QueryAuthIdModel.QueryAuthIdModelBuilder queryAuthIdModel = QueryAuthIdModel.builder();

        queryAuthIdModel.workerId( worker.getId() );

        return queryAuthIdModel.build();
    }
}
