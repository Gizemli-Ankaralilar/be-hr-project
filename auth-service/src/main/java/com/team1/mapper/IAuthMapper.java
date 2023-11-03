package com.team1.mapper;

import com.team1.request.RegisterSaveCompanyDto;
import com.team1.request.RegisterRequestUserDto;
import com.team1.request.RegisterRequestVisitorDto;
import com.team1.response.RegisterResponseVisitorDto;
import com.team1.rabbitmq.model.CreateAuthModel;
import com.team1.rabbitmq.model.MailRegisterModel;
import com.team1.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {

    IAuthMapper INSTANCE = Mappers.getMapper(IAuthMapper.class);
    Auth toAuth(RegisterRequestVisitorDto dto);
    RegisterResponseVisitorDto toRegisterResponseDto(Auth auth);

    @Mapping(source = "id",target = "authId")
    RegisterRequestUserDto toUserSaveRequestDto(Auth auth);

    Auth toAuthCompany(RegisterSaveCompanyDto dto);

    Auth toSaveAutRabbit(CreateAuthModel model);

    MailRegisterModel toMailModel(Auth auth);

}