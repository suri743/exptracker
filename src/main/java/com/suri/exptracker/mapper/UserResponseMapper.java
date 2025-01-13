package com.suri.exptracker.mapper;

import com.suri.exptracker.dto.GroupDto;
import com.suri.exptracker.dto.UserResponseDto;
import com.suri.exptracker.entity.Group;
import com.suri.exptracker.entity.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserResponseMapper {

    @Mapping(target = "groups", ignore = true)
    public abstract UserResponseDto mapEntityToDto(User user);

    @Mapping(target = "groups", ignore = true)
    public abstract User mapDtoToEntity(UserResponseDto userResponseDto);

    public abstract List<UserResponseDto> mapEntityListToDtoList(List<User> users);

    public abstract List<User> mapDtoListToEntityList(List<UserResponseDto> userResponseDtos);

    @AfterMapping
    protected void mapGroups(User user, @MappingTarget UserResponseDto.UserResponseDtoBuilder userDto) {
        userDto.groups(getGroupDtos(user));
    }

    @AfterMapping
    protected void mapGroups(UserResponseDto userResponseDto,@MappingTarget User.UserBuilder<?,?> user) {
        user.groups(getGroups(userResponseDto));
    }

    private List<GroupDto> getGroupDtos(User user) {
        if(user.getGroups() == null){
            return null;
        }

        return user.getGroups()
            .stream()
            .map(group -> GroupDto.builder()
                .id(group.getId())
                .name(group.getName())
                .description(group.getDescription()).build())
            .collect(Collectors.toList());

    }

    private List<Group> getGroups(UserResponseDto userResponseDto) {
        if(userResponseDto.getGroups() == null){
            return List.of();
        }
        return userResponseDto
            .getGroups()
            .stream()
            .map(group -> Group.builder()
                .id(group.getId())
                .name(group.getName())
                .description(group.getDescription()).build())
            .collect(Collectors.toList());
    }

}
