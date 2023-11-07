package com.team1.mapper;

import com.team1.dto.request.SendMailRequestDto;
import com.team1.repository.entity.MailProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IMailMapper {
    IMailMapper INSTANCE= Mappers.getMapper(IMailMapper.class);

    MailProfile toUserProfile(SendMailRequestDto dto);
}
