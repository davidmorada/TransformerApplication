package com.transformers.application.exception;

import lombok.Getter;

@Getter
public class NoRecordFoundException extends Exception {

    private String message;

    public NoRecordFoundException(String message) {
        this.message = message;
    }

    public static NoRecordFoundException getMessage(String message) {
        return new NoRecordFoundException(message);
    }
}
