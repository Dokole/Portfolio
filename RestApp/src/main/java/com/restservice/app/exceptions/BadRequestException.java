package com.restservice.app.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String text) {
        super(text);
    }
}
