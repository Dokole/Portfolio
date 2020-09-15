package com.producingservice.app.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException (String text) {
        super(text);
    }
}
