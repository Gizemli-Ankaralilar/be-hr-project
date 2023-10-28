package com.team1.mapper;

import com.team1.dto.request.RegisterRequestCompanyDto;
import com.team1.dto.request.RegisterRequestUserDto;
import com.team1.dto.request.RegisterRequestVisitorDto;
import com.team1.dto.response.RegisterResponseVisitorDto;
import com.team1.rabbitmq.model.MailActivateModel;
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


    Auth toAuth(RegisterRequestCompanyDto dto);
//    @Mapping(source = "id", target = "authId")
//    RegisterRequestCompanyDto toCompanyRegisterDto(Auth auth);

    MailRegisterModel toMailModel(Auth auth);
    MailActivateModel fromAuthToMailActivateModel(final Auth auth);

}