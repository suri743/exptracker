package com.suri.exptracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidRequestException(InvalidRequestException ex) {
        return ResponseEntity.status(ex.getStatus())
            .body(new ErrorResponseDto(ex.getError()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericException(Exception ex) {
        // Optionally, check if it's an InvalidRequestException and rethrow
        if (ex instanceof InvalidRequestException invalidRequestException) {
            return handleInvalidRequestException(invalidRequestException);
        }
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorResponseDto(new ErrorResponseDto.Error
                                           ("INTERNAL_SERVER_ERROR",
                                            "An unexpected error occurred.", null)));
    }
}