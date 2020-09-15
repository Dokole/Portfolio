package com.transcacheservice.cacheapp.dto.rest;

import org.springframework.hateoas.EntityModel;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public class ItemRest extends BaseObjectRest {
    protected String name;

    protected String description;

    protected int price;

    public ItemRest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
