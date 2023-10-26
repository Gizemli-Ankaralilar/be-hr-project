package com.team1.mapper;

import com.team1.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {

    IAuthMapper INSTANCE = Mappers.getMapper(IAuthMapper.class);
    User toAuth(RegisterRequestVisitorDto dto);
    RegisterResponseVisitorDto toRegisterResponseDto(User auth);

    User toAuth(RegisterRequestCompanyDto dto);
//    @Mapping(source = "id", target = "authId")
//    RegisterRequestCompanyDto toCompanyRegisterDto(Auth auth);

}