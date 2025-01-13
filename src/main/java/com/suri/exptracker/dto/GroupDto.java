package com.suri.exptracker.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GroupDto {
    Integer id;
    String name;
    String description;
    UserDto createdBy;
    List<UserDto> members;
    Map<UserDto, Double> balances;
    List<ExpenseDto> expenses;
    List<TransactionDto> transactions;
}
