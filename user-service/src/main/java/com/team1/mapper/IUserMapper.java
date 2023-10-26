package com.team1.mapper;

import com.team1.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);
//    @Mapping(source = "id", target = "authId")
//    RegisterRequestCompanyDto toCompanyRegisterDto(Auth auth);

}