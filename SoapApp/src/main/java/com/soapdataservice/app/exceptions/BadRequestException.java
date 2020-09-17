package com.soapdataservice.app.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException (String text) {
        super(text);
    }
}
