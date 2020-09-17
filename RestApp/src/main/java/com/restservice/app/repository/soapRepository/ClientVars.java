package com.restservice.app.repository.soapRepository;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 *
 * Variables for SOAP clients
 */

public enum ClientVars {
    SOAP_SERVICE_URI("http://localhost:8080/ws"),
    SOAP_ACTION("http://com/soapdataservice/app/dto");

    private String value;

    ClientVars(String s) {
        value = s;
    }

    public String getValue() {
        return value;
    }
}
