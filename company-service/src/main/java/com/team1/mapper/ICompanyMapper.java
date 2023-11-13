package com.team1.mapper;

import com.team1.dto.request.SpendingDto;
import com.team1.dto.response.CompanyListenerResponseDto;
import com.team1.dto.response.ResponceIncomeDto;
import com.team1.dto.response.ResponseSpendingDto;
import com.team1.rabbitmq.model.AuthCompanyModel;
import com.team1.repository.entity.Company;
import com.team1.repository.entity.Finance;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICompanyMapper {

    ICompanyMapper INSTANCE = Mappers.getMapper(ICompanyMapper.class);
    Company toAuthCompany(AuthCompanyModel model);

    ResponseSpendingDto toSpendingFinance(Finance finance);
    ResponceIncomeDto toIncomeFinance(Finance finance);

    CompanyListenerResponseDto toCompanyListener (Company company);

}
