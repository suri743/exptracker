package com.suri.exptracker.service;

import com.suri.exptracker.dto.requestdtos.ExpenseRequestDto;
import com.suri.exptracker.dto.ExpenseDto;
import com.suri.exptracker.dto.GroupDto;
import com.suri.exptracker.dto.SplitDto;
import com.suri.exptracker.dto.requestdtos.UserDto;
import com.suri.exptracker.entity.Expense;
import com.suri.exptracker.entity.constant.EntityName;
import com.suri.exptracker.exceptions.ErrorCode;
import com.suri.exptracker.exceptions.ErrorResponseDto;
import com.suri.exptracker.exceptions.InvalidRequestException;
import com.suri.exptracker.mapper.ExpenseMapper;
import com.suri.exptracker.repository.ExpenseRepository;
import com.suri.exptracker.service.validations.ValidationManager;
import com.suri.exptracker.service.validations.ValidationOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;
    private final ValidationManager<Expense> validationManager;

    private static final String EXPENSE = "Expense";

    public ExpenseDto createExpense(ExpenseRequestDto expenseRequestDto) {

        ExpenseDto expenseDto = ExpenseDto
            .builder()
            .name(expenseRequestDto.getName())
            .note(expenseRequestDto.getNote())
            .amount(expenseRequestDto.getAmount())
            .paidBy(getUserDto(expenseRequestDto.getPaidBy()))
            .group(getGroupDto(expenseRequestDto.getGroup()))
            .owed(getOwedBy(expenseRequestDto).isEmpty() ? null : getOwedBy(expenseRequestDto))
            .build();

        Expense expense = expenseMapper.mapDtoToEntity(expenseDto);
        System.out.println("ExpenseService.createExpense");
        validationManager.apply(expense,null, ValidationOperation.CREATE, EntityName.EXPENSE);
        System.out.println("ExpenseService.createExpense: Validation passed");

        return expenseMapper.mapEntityToDto(expenseRepository.save(expense));
    }

    public ExpenseDto getExpense(Integer id) {
        return expenseMapper.mapEntityToDto(expenseRepository.findById(id)
                                                .orElseThrow(() ->
        new InvalidRequestException(
            ErrorCode.VALIDATION_ERROR,
            HttpStatus.BAD_REQUEST,
            new Object[]{EXPENSE},
            Collections.singletonList(
                new ErrorResponseDto.ValidationErrorDetail(
                    ErrorCode.DATA_NOT_FOUND.getCode(),
                    ErrorCode.DATA_NOT_FOUND.getFormattedMessage(), // Format the message with both placeholders
                    EXPENSE)))));
    }

    public List<ExpenseDto> getExpenses(){
        return expenseMapper.mapEntityListToDtoList(expenseRepository.findAll());
    }

    private List<SplitDto> getOwedBy(ExpenseRequestDto expenseRequestDto){

        if(Objects.isNull(expenseRequestDto.getOwed()))
            return List.of();

        return expenseRequestDto.getOwed().stream()
            .filter(Objects::nonNull)
            .map(splitRequestDto -> SplitDto.builder()
                .user(getUserDto(splitRequestDto.getUser()))
                .amount(splitRequestDto.getAmount())
                .build())
            .toList();
    }

    private static UserDto getUserDto(Integer id) {

        if(Objects.isNull(id))
            return null;

        return UserDto.builder().id(id).build();
    }

    private static GroupDto getGroupDto(Integer groupId) {

        if(Objects.isNull(groupId))
            return null;

        return GroupDto.builder().id(groupId).build();
    }

}
