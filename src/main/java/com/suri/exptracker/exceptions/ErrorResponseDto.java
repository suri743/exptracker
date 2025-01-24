package com.suri.exptracker.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class ErrorResponseDto implements Serializable {

    private Error error;

    @Data
    @AllArgsConstructor
    public static class Error {
        private String code;
        private String message;
        private List<ValidationErrorDetail> details;
    }

    @Data
    @AllArgsConstructor
    public static class ValidationErrorDetail {
        private String code;
        private String message;
        private String target;
    }
}