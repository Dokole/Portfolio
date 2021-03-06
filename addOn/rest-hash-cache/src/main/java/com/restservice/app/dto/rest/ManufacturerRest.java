package com.restservice.app.dto.rest;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public class ManufacturerRest extends BaseObjectRest {
    protected String name;

    protected AddressRest address;

    public ManufacturerRest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressRest getAddress() {
        return address;
    }

    public void setAddress(AddressRest address) {
        this.address = address;
    }
}
