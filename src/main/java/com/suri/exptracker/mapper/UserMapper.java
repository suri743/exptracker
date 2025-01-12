package com.suri.exptracker.mapper;

import com.suri.exptracker.dto.GroupDto;
import com.suri.exptracker.dto.UserDto;
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
public abstract class UserMapper {

    @Mapping(target = "groups", ignore = true)
    public abstract UserDto mapEntityToDto(User user);

    @Mapping(target = "groups", ignore = true)
    public abstract User mapDtoToEntity(UserDto userDto);

    public abstract List<UserDto> mapEntityListToDtoList(List<User> users);

    public abstract List<User> mapDtoListToEntityList(List<UserDto> userDtos);

    @AfterMapping
    protected void mapGroups(User user, @MappingTarget UserDto.UserDtoBuilder userDto) {
        userDto.groups(getGroupDtos(user));
    }

    @AfterMapping
    protected void mapGroups(UserDto userDto, @MappingTarget User.UserBuilder<?,?> user) {
        user.groups(getGroups(userDto));
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

    private List<Group> getGroups(UserDto userDto) {
        if(userDto.getGroups() == null){
            return List.of();
        }
        return userDto.getGroups()
            .stream()
            .map(group -> Group.builder()
                .id(group.getId())
                .name(group.getName())
                .description(group.getDescription()).build())
            .collect(Collectors.toList());
    }

}
