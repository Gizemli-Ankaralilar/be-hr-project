package com.team1.mapper;

import com.team1.dto.request.SaveWorkerDto;
import com.team1.dto.response.RegisterResponseCompanyDto;
import com.team1.rabbitmq.model.SaveCompanyModel;
import com.team1.repository.entity.Company;
import com.team1.repository.entity.Worker;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICompanyMapper {

    ICompanyMapper INSTANCE = Mappers.getMapper(ICompanyMapper.class);
    Company toSaveCompanyRabbit(SaveCompanyModel model);

}
