package com.team1.mapper;

import com.team1.dto.request.RegisterRequestCompanyDto;
import com.team1.dto.request.RegisterRequestVisitorDto;
import com.team1.dto.request.SendMailRequestDto;
import com.team1.dto.response.RegisterResponseVisitorDto;
import com.team1.rabbitmq.model.CompanyWorkerAuthModel;
import com.team1.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {

    IAuthMapper INSTANCE = Mappers.getMapper(IAuthMapper.class);
    Auth toRegisterAuth(RegisterRequestVisitorDto dto);
    Auth toRegisterCompany(RegisterRequestCompanyDto dto);
    Auth toRegisterCompany(CompanyWorkerAuthModel model);
    RegisterResponseVisitorDto toRegisterResponseDto(Auth auth);

    @Mapping(source = "id",target = "authId")
    SendMailRequestDto toSendMailRequestDto(Auth auth);

    RegisterRequestVisitorDto fromRequestCompanyDtoToRequestVisitorDto (RegisterRequestCompanyDto dto);

}