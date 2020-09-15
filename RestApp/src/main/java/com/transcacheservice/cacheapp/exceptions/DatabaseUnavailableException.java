package com.transcacheservice.cacheapp.exceptions;

public class DatabaseUnavailableException extends RuntimeException {
    public DatabaseUnavailableException(String text) {
        super(text);
    }
}
