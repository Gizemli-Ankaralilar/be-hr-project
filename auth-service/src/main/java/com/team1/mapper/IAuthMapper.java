package com.team1.mapper;

import com.team1.dto.request.RegisterRequestVisitorDto;
import com.team1.dto.response.RegisterResponseVisitorDto;
import com.team1.rabbitmq.model.MailModel;
import com.team1.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {

    IAuthMapper INSTANCE = Mappers.getMapper(IAuthMapper.class);
    Auth toAuth(RegisterRequestVisitorDto dto);
    RegisterResponseVisitorDto toRegisterResponseDto(Auth auth);

<<<<<<< HEAD
    @Mapping(source = "id", target = "authId")
    SaveUserRequestDto toSaveUserRequestDto(Auth auth);

    MailModel toMailModel(Auth auth);



//    Auth toAuth(RegisterSaveCompanyDto dto);
=======
    //Auth toAuth(RegisterSaveCompanyDto dto);
>>>>>>> master
//    @Mapping(source = "id", target = "authId")
//    RegisterRequestCompanyDto toCompanyRegisterDto(Auth auth);

}