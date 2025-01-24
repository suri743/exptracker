package com.suri.exptracker.service;

import com.suri.exptracker.dto.GroupDto;
import com.suri.exptracker.dto.requestdtos.GroupRequestDto;
import com.suri.exptracker.dto.requestdtos.UserDto;
import com.suri.exptracker.entity.Group;
import com.suri.exptracker.mapper.GroupMapper;
import com.suri.exptracker.repository.GroupRepository;
import com.suri.exptracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final UserRepository userRepository;

    public GroupDto createGroup(GroupRequestDto groupRequestDto) {
        Group group = Group.builder()
            .name(groupRequestDto.getName())
            .description(groupRequestDto.getDescription())
            .createdBy(userRepository.findById(groupRequestDto.getCreatedBy().getId())
                           .orElseThrow(() -> new RuntimeException("User not found")))
            .members(userRepository.findAllById(
                groupRequestDto.getMembers()
                    .stream()
                    .map(UserDto::getId)
                    .toList()))
            .build();

        return groupMapper.mapEntityToDto(groupRepository.save(group));
    }

    public GroupDto getGroupById(Integer id) {
        return groupMapper.mapEntityToDto(groupRepository.findById(id).orElse(null));
    }
}
