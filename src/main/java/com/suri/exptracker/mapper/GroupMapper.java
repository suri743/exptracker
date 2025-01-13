package com.suri.exptracker.mapper;

import com.suri.exptracker.dto.GroupDto;
import com.suri.exptracker.entity.Group;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserMapper.class,ExpenseMapper.class})
public abstract class GroupMapper {

    public abstract GroupDto mapEntityToDto(Group group);

    public abstract Group mapDtoToEntity(GroupDto groupDto);

    public abstract List<GroupDto> mapEntityListToDtoList(List<Group> groups);

    public abstract List<Group> mapDtoListToEntityList(List<GroupDto> groupDtos);

}
