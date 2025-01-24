package com.suri.exptracker.dto.requestdtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExpenseRequestDto {
    Integer id;
    String name;
    String note;
    double amount;
    Integer group;
    Integer paidBy;
    List<SplitRequestDto> owed;
}
