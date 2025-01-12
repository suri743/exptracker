package com.suri.exptracker.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class UserDto {
    Integer id;
    String name;
    String email;
    String mobile;
    List<GroupDto> groups;
}
