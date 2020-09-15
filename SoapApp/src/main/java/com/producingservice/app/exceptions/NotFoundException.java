package com.producingservice.app.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String e) {
        super(e);
    }
}
