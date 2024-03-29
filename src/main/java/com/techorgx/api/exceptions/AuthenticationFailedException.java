package com.techorgx.api.exceptions;

import org.springframework.http.HttpStatus;

public class AuthenticationFailedException extends RuntimeException {

    private final HttpStatus httpStatus;

    public AuthenticationFailedException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
