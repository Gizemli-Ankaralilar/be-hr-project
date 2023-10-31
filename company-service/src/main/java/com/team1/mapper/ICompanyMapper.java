package com.team1.mapper;

import com.team1.dto.request.RegisterSaveCompanyDto;
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
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICompanyMapper {

    ICompanyMapper INSTANCE = Mappers.getMapper(ICompanyMapper.class);

    RegisterRequestVisitorDto toRequestVisitorDto(Company company);


    Company toCompany(SaveCompanyDto dto);

    @Mapping(source = "id", target = "companyId")
    CreateCompanyAuthModel toSaveCompanyRabbit(Company company);
    CreateAuthModel toSaveAutRabbit(Company company);
    RegisterResponseCompanyDto toRegisterResponseDto(Company company);

    Worker toSaveWorker(SaveWorkerDto dto);


    CreateWorkerAuthModel toSaveWorkerAuth(Worker worker);
    @Mapping(source = "id", target = "workerId")
    QueryAuthIdModel toWorkerIdAuth(Worker worker);


}
