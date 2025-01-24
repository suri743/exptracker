package com.suri.exptracker.service.validations;

import com.suri.exptracker.entity.Expense;
import com.suri.exptracker.entity.Group;
import com.suri.exptracker.entity.User;
import com.suri.exptracker.entity.constant.EntityName;
import com.suri.exptracker.exceptions.ErrorCode;
import com.suri.exptracker.repository.GroupRepository;
import com.suri.exptracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

import static com.suri.exptracker.util.ExpTrackerHelper.throwValidation;


@Component
@ValidationProperty(entityName = {EntityName.EXPENSE},
    operation = {ValidationOperation.CREATE, ValidationOperation.UPDATE})
@RequiredArgsConstructor
public class ExpenseValidation implements Validations<Expense> {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    private static final String EXPENSE = "Expense";


    @Override
    public void applyValidation(Expense curImg, Expense befImg, ValidationOperation operation) {
        if (!ValidationOperation.CREATE.equals(operation)) {
            return;
        }
        validateExpense(curImg);
        validateUser(curImg.getPaidBy());
        validateGroup(curImg.getGroup());
        validateAmount(curImg.getAmount());
        validateOwed(Collections.singletonList(curImg.getOwed()));
    }

    private void validateExpense(Expense expense) {
        if (expense == null) {
            throwValidation(ErrorCode.INVALID_DATA_PROVIDED, "data", EXPENSE, "expense");
        }
    }

    private void validateUser(User user) {
        if (user == null || user.getId() <= 0) {
            throwValidation(ErrorCode.INVALID_USER, "User", EXPENSE, Expense.Fields.paidBy);
        }else if (userRepository.findById(user.getId()).isEmpty()) {
            throwValidation(ErrorCode.DATA_NOT_FOUND, "User", EXPENSE, Expense.Fields.paidBy);
        }
    }

    private void validateGroup(Group group) {
        if (group == null || group.getId() <= 0) {
            throwValidation(ErrorCode.INVALID_GROUP, "Group", EXPENSE, Expense.Fields.group);
        }else if (groupRepository.findById(group.getId()).isEmpty()) {
            throwValidation(ErrorCode.DATA_NOT_FOUND, "Group", EXPENSE, Expense.Fields.group);
        }
    }

    private void validateAmount(double amount) {
        if (amount <= 0) {
            throwValidation(ErrorCode.INVALID_DATA_PROVIDED, Expense.Fields.amount, EXPENSE, Expense.Fields.amount);
        }
    }

    private void validateOwed(List<Object> owed) {
        if (owed == null || owed.isEmpty()) {
            throwValidation(ErrorCode.INVALID_DATA_PROVIDED, Expense.Fields.owed, EXPENSE, Expense.Fields.owed);
        }
    }
}
