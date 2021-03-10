package com.transformers.application.exception;

import lombok.Getter;

@Getter
public class NullPointerException extends Exception {

    private String message;

    public NullPointerException(String message) {
        this.message = message;
    }

    private static NullPointerException createMsg(String message) {
        return new NullPointerException(message);
    }

}
