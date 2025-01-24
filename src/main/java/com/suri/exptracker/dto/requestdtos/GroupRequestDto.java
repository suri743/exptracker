package com.suri.exptracker.dto.requestdtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GroupRequestDto {
    Integer id;
    String name;
    String description;
    UserDto createdBy;
    List<UserDto> members;
}
