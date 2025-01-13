package com.suri.exptracker.mapper;

import com.suri.exptracker.dto.UserDto;
import com.suri.exptracker.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserDto mapEntityToDto(User user);

    User mapDtoToEntity(UserDto userDto);

    List<UserDto> mapEntityListToDtoList(List<User> users);

    List<User> mapDtoListToEntityList(List<UserDto> userDtos);

}
