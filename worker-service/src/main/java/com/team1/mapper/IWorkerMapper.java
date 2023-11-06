package com.team1.mapper;

import com.team1.repository.entity.Worker;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface IWorkerMapper {

    IWorkerMapper INSTANCE = Mappers.getMapper(IWorkerMapper.class);

}
