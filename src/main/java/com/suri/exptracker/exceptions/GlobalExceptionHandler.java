package com.suri.exptracker.exceptions;

import com.suri.exptracker.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseDto<Void>> handleTicketNotFoundException(UserNotFoundException ex) {
        ResponseDto<Void> response = new ResponseDto<>("ERROR", ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto<Void>> handleGenericException(Exception ex) {
        return buildErrorResponse("ERROR", "An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ResponseDto<Void>> buildErrorResponse(String status,
                                                                 String error,
                                                                 HttpStatus httpStatus) {
        ResponseDto<Void> response = new ResponseDto<>(status, error, null);
        return new ResponseEntity<>(response, httpStatus);
    }
}