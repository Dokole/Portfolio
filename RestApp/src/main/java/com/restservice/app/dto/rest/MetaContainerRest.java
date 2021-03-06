package com.restservice.app.dto.rest;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public class MetaContainerRest {

    protected Long id;
    protected String name;

    public MetaContainerRest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MetaContainer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
