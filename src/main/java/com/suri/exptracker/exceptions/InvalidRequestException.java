package com.suri.exptracker.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class InvalidRequestException extends RuntimeException {
    private final HttpStatus status;
    private final ErrorResponseDto.Error error;

    public InvalidRequestException(ErrorCode errorCode, HttpStatus status, Object[] args, List<ErrorResponseDto.ValidationErrorDetail> details) {
        super(Objects.requireNonNull(errorCode, "ErrorCode must not be null").getFormattedMessage(args));
        this.status = Objects.requireNonNull(status, "HttpStatus must not be null");

        String errorCodeWithNamespace = "ExpenseTracker." + errorCode.getCode();
        String formattedMessage = errorCode.getFormattedMessage(args);

        this.error = new ErrorResponseDto.Error(
            errorCodeWithNamespace,
            formattedMessage,
            details != null ? details : new ArrayList<>()
        );
    }

    public InvalidRequestException(ErrorCode errorCode, HttpStatus status, Object[] args) {
        this(errorCode, status, args, new ArrayList<>());
    }
}