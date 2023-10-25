package com.team1.mapper;

import com.team1.dto.request.RegisterRequestCompanyDto;
import com.team1.dto.request.RegisterRequestEmployeeDto;
import com.team1.dto.response.RegisterResponseCompanyDto;
import com.team1.repository.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICompanyMapper {

    ICompanyMapper INSTANCE = Mappers.getMapper(ICompanyMapper.class);

    Company toCompany(RegisterRequestCompanyDto dto);
    RegisterResponseCompanyDto toRegisterResponseDto(Company company);

//    @Mapping(source = "id", target = "employeeId")
//    RegisterRequestEmployeeDto toRegisterRequestDto(Company company);

//    @Mapping(source = "id",target = "employeeId")
//    RegisterRequestEmployeeDto toRegisterRequestDto(Company RegisterRequestEmployeeDto);

}
