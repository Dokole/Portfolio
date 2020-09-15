package com.transcacheservice.cacheapp.dto.rest;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public class CategoryRest extends BaseObjectRest {

    protected String name;

    public CategoryRest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
