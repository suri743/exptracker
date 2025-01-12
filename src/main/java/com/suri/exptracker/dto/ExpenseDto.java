package com.suri.exptracker.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExpenseDto {
    Integer id;
    String name;
    String note;
    double amount;
    GroupDto group;
    UserDto paidBy;
    List<SplitDto> owed;
}
