package com.transcacheservice.cacheapp.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String text) {
        super(text);
    }
}
