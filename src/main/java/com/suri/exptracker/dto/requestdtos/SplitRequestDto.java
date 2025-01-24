package com.suri.exptracker.dto.requestdtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SplitRequestDto {
    Integer id;
    Integer user;
    double amount;
}
