package com.suri.exptracker.dto.requestdtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class UserBalanceDto {
    Integer userId; // or replace with UserDto if needed
    Double balance;
}