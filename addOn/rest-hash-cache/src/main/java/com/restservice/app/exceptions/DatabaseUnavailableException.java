package com.restservice.app.exceptions;

public class DatabaseUnavailableException extends RuntimeException {
    public DatabaseUnavailableException(String text) {
        super(text);
    }
}
