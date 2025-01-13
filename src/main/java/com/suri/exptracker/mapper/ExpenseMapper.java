package com.suri.exptracker.mapper;

import com.suri.exptracker.dto.ExpenseDto;
import com.suri.exptracker.dto.GroupDto;
import com.suri.exptracker.entity.Expense;
import com.suri.exptracker.entity.Group;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {SplitMapper.class, UserMapper.class})
public abstract class ExpenseMapper {

    @Mapping(target = "group", ignore = true)
    public abstract ExpenseDto mapEntityToDto(Expense expense);

    @Mapping(target = "group", ignore = true)
    public abstract Expense mapDtoToEntity(ExpenseDto expenseDto);

    public abstract List<ExpenseDto> mapEntityListToDtoList(List<Expense> expenses);

    public abstract List<Expense> mapDtoListToEntityList(List<ExpenseDto> expenseDtos);

    @AfterMapping
    protected void mapGroup(Expense expense, @MappingTarget ExpenseDto.ExpenseDtoBuilder expenseDto) {
        if(Objects.isNull(expense.getGroup()))
            return;

        expenseDto.group(GroupDto.builder()
                             .id(expense.getGroup().getId())
                             .description(expense.getGroup().getDescription())
                             .name(expense.getGroup().getName())
                             .build());
    }

    @AfterMapping
    protected void mapGroup(ExpenseDto expenseDto, @MappingTarget Expense.ExpenseBuilder<?,?> expense) {
        if(Objects.isNull(expenseDto.getGroup()))
            return;

        expense.group(Group.builder()
                          .id(expenseDto.getGroup().getId())
                          .description(expenseDto.getGroup().getDescription())
                          .name(expenseDto.getGroup().getName())
                          .build());
    }

}
