package com.transcacheservice.cacheapp.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String text) {
        super(text);
    }
}
