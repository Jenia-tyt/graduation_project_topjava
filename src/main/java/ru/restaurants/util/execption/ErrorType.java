package ru.restaurants.util.execption;

import org.springframework.http.HttpStatus;

public enum ErrorType {
    APP_ERROR("error.appError", HttpStatus.INTERNAL_SERVER_ERROR),
    DATA_NOT_FOUND("error.dataNotFound", HttpStatus.UNPROCESSABLE_ENTITY),
    DATA_ERROR("error.dataError", HttpStatus.CONFLICT),
    VALIDATION_ERROR("error.validationError", HttpStatus.UNPROCESSABLE_ENTITY),
    WRONG_REQUEST("error.wrongRequest", HttpStatus.NOT_FOUND),
    VOTE_ERROR("error.vote", HttpStatus.PRECONDITION_FAILED),
    ERROR_CHANGE("error.change", HttpStatus.NOT_ACCEPTABLE);

    private final String errorCode;
    private final HttpStatus status;

    ErrorType(String errorCode, HttpStatus status) {
        this.errorCode = errorCode;
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getStatus() {
        return status;
    }
}