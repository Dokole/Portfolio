package com.restservice.app.dto.rest;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public abstract class BaseObjectRest {

    protected String id;

    protected Long version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

}
