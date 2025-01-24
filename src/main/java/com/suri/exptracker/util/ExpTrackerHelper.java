package com.suri.exptracker.util;

import com.suri.exptracker.exceptions.ErrorCode;
import com.suri.exptracker.exceptions.ErrorResponseDto;
import com.suri.exptracker.exceptions.InvalidRequestException;
import org.springframework.http.HttpStatus;

import java.util.Collections;

public class ExpTrackerHelper {

    private ExpTrackerHelper() {
    }

    public static void throwValidation(ErrorCode errorCode, String entityName, String serviceName, String fieldName) {
        throw new InvalidRequestException(
            ErrorCode.VALIDATION_ERROR,
            HttpStatus.BAD_REQUEST,
            new Object[]{entityName, serviceName}, // Pass both placeholders
            Collections.singletonList(
                new ErrorResponseDto.ValidationErrorDetail(
                    errorCode.getCode(),
                    errorCode.getFormattedMessage(entityName, serviceName), // Format the message with both placeholders
                    fieldName)
            )
        );
    }
}
