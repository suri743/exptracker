package com.suri.exptracker.exceptions;

import lombok.Getter;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

@Getter
public enum ErrorCode {

    GENERIC_RESPONSE_MESSAGE("10001"),
    VALIDATION_ERROR("10002"),
    INVALID_DATA_PROVIDED("10003"),
    EMPTY_USERNAME("10004"),
    DATA_NOT_FOUND("10005"),
    INVALID_GROUP("10006"),
    INVALID_USER("10007");

    private final String code;
    private static MessageSource messageSource;

    ErrorCode(String code) {
        this.code = code;
    }

    public String getFormattedMessage(Object... args) {
        return messageSource.getMessage(this.name(), args, LocaleContextHolder.getLocale());
    }

    @Component
    public static class ErrorCodeMessageSourceInjector {

        private final MessageSource injectedMessageSource;

        public ErrorCodeMessageSourceInjector(MessageSource messageSource) {
            this.injectedMessageSource = messageSource;
        }

        @PostConstruct
        public void postConstruct() {
            ErrorCode.messageSource = this.injectedMessageSource;
        }
    }
}