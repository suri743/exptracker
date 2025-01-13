package com.suri.exptracker.mapper;

import com.suri.exptracker.dto.GroupDto;
import com.suri.exptracker.dto.TransactionDto;
import com.suri.exptracker.entity.Group;
import com.suri.exptracker.entity.Transaction;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserMapper.class})
public abstract class TransactionMapper {

    @Mapping(target = "group", ignore = true)
    public abstract TransactionDto mapEntityToDto(Transaction transaction);

    @Mapping(target = "group", ignore = true)
    public abstract Transaction mapDtoToEntity(TransactionDto transactionDto);

    public abstract List<TransactionDto> mapEntityListToDtoList(List<Transaction> transactions);

    public abstract List<Transaction> mapDtoListToEntityList(List<TransactionDto> transactionDtos);

    @AfterMapping
    protected void mapGroup(Transaction transaction, @MappingTarget TransactionDto.TransactionDtoBuilder transactionDto) {
        if (transaction.getGroup() != null) {
            transactionDto.group(GroupDto
                                     .builder()
                                     .id(transaction.getGroup().getId())
                                     .description(transaction.getGroup().getDescription())
                                     .name(transaction.getGroup().getName())
                                     .build());
        }
    }

    @AfterMapping
    protected void mapGroup(TransactionDto transactionDto, @MappingTarget Transaction.TransactionBuilder<?, ?> transaction) {
        if (transactionDto.getGroup() != null) {
            transaction.group(Group.builder()
                                   .id(transactionDto.getGroup().getId())
                                   .description(transactionDto.getGroup().getDescription())
                                   .name(transactionDto.getGroup().getName())
                                   .build());
        }
    }

}
