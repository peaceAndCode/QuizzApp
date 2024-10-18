package com.peaceandcode.quizapp.mapper;

import com.peaceandcode.quizapp.dto.RequestLoginRegisterDto;
import com.peaceandcode.quizapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User entity(RequestLoginRegisterDto user);
}
