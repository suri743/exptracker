package com.suri.exptracker.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.suri.exptracker.dto.requestdtos.UserDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TransactionDto {
    Integer id;
    String note;
    double amount;
    GroupDto group;
    UserDto paidBy;
    UserDto paidTo;
}
