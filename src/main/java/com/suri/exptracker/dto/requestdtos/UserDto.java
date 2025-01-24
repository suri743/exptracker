package com.suri.exptracker.dto.requestdtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserDto {
    Integer id;
    String name;
    String email;
    String mobile;
}
