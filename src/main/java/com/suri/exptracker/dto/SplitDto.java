package com.suri.exptracker.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SplitDto {
    Integer id;
    UserDto user;
    double amount;
    ExpenseDto expense;
}
