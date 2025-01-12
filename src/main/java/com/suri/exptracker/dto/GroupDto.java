package com.suri.exptracker.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.suri.exptracker.entity.Expense;
import com.suri.exptracker.entity.Transaction;
import com.suri.exptracker.entity.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GroupDto {
    Integer id;
    String name;
    String description;
    UserDto createdBy;
    List<UserDto> members;
    Map<User, Double> balances;
    List<Expense> expenses;
    List<Transaction> transactions;
}
