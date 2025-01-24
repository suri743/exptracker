package com.suri.exptracker.service.validations;

import com.suri.exptracker.entity.User;
import com.suri.exptracker.entity.constant.EntityName;
import com.suri.exptracker.exceptions.ErrorCode;
import org.springframework.stereotype.Component;

import static com.suri.exptracker.util.ExpTrackerHelper.throwValidation;

@Component
@ValidationProperty(entityName = {EntityName.USER},
    operation = {ValidationOperation.CREATE, ValidationOperation.UPDATE})
public class UserValidation implements Validations<User> {

    @Override
    public void applyValidation(User curImg, User befImg, ValidationOperation operation) {
        if (operation.equals(ValidationOperation.CREATE) && curImg == null) {
            throwValidation(ErrorCode.INVALID_DATA_PROVIDED, "User Data", "User", "user");
        }else if(curImg.getName() == null || curImg.getName().isEmpty()) {
            throwValidation(ErrorCode.EMPTY_USERNAME, null, "User", User.Fields.name);
        }

        if(operation.equals(ValidationOperation.UPDATE)
           && (curImg.getId() == 0 || curImg.getId() <= 0))
                throwValidation(ErrorCode.DATA_NOT_FOUND, "User", "User","userId");
    }
}