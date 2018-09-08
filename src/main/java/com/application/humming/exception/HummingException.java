package com.application.humming.exception;

import lombok.Getter;

public class HummingException extends Exception {

    @Getter
    private String message;

    public HummingException(final String message) {
        super(message);
        this.message = message;
    }

    public HummingException(final String message, final Throwable cause) {
        super(message, cause);
        this.message = message;
    }
}
