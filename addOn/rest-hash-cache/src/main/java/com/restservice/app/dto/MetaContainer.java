package com.restservice.app.dto;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public class MetaContainer {

    protected Long id;

    protected String name;

    public MetaContainer() {
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
