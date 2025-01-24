package com.suri.exptracker.mapper;

import com.suri.exptracker.dto.ExpenseDto;
import com.suri.exptracker.dto.GroupDto;
import com.suri.exptracker.dto.SplitDto;
import com.suri.exptracker.dto.requestdtos.UserDto;
import com.suri.exptracker.entity.Expense;
import com.suri.exptracker.entity.Group;
import com.suri.exptracker.entity.Split;
import com.suri.exptracker.entity.User;
import org.springframework.stereotype.Component;

@Component
public class SplitMapper {

    public SplitDto mapEntityToDto(Split split){
        if(split == null){
            return null;
        }

        return SplitDto.builder()
            .id(split.getId())
            .amount(split.getAmount())
            .user(getUserDto(split.getUser()))
            .expense(getExpenseDto(split))
            .build();
    }

    public Split mapDtoToEntity(SplitDto splitDto){
        if(splitDto == null){
            return null;
        }

        return Split.builder()
            .id(splitDto.getId())
            .amount(splitDto.getAmount())
            .user(getUserEntity(splitDto.getUser()))
            .expense(getExpenseEntity(splitDto.getExpense()))
            .build();
    }

    private static User getUserEntity(UserDto userResponseDto) {
        if(userResponseDto == null)
            return null;

        return User
            .builder()
            .id(userResponseDto.getId())
            .name(userResponseDto.getName())
            .mobile(userResponseDto.getMobile())
            .email(userResponseDto.getEmail())
            .build();
    }

    private static Expense getExpenseEntity(ExpenseDto expenseDto) {
        if(expenseDto == null)
            return null;

        return Expense
            .builder()
            .id(expenseDto.getId())
            .note(expenseDto.getNote())
            .paidBy(getUserEntity(expenseDto.getPaidBy()))
            .amount(expenseDto.getAmount())
            .group(Group.builder()
                       .id(expenseDto
                               .getGroup().getId())
                       .name(expenseDto
                                 .getGroup().getName())
                       .description(expenseDto
                                        .getGroup().getDescription())
                       .build())
            .build();
    }

    private static ExpenseDto getExpenseDto(Split split) {
        if(split.getExpense() == null)
            return null;

        return ExpenseDto
            .builder()
            .id(split.getExpense().getId())
            .note(split.getExpense().getNote())
            .paidBy(getUserDto(split.getExpense().getPaidBy()))
            .amount(split.getExpense().getAmount())
            .group(GroupDto.builder()
                       .id(split.getExpense().getGroup().getId())
                       .name(split.getExpense().getGroup().getName())
                       .description(split.getExpense().getGroup().getDescription())
                       .build())
            .build();
    }

    private static UserDto getUserDto(User user) {
        if(user == null)
            return null;

        return UserDto.builder()
            .id(user.getId())
            .name(user.getName())
            .mobile(user.getMobile())
            .email(user.getEmail())
            .build();
    }

}
