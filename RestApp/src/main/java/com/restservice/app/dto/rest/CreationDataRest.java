package com.restservice.app.dto.rest;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 *
 * Data from those rest dto object are supposed to be used for create, update and delete requests.
 */

public class CreationDataRest {

    protected List<MetaContainerRest> brands = new LinkedList<>();
    protected List<MetaContainerRest> manufacturers = new LinkedList<>();
    protected List<MetaContainerRest> categories = new LinkedList<>();
    protected List<MetaContainerRest> items = new LinkedList<>();

    public CreationDataRest() {
    }

    public CreationDataRest(List<MetaContainerRest> brands, List<MetaContainerRest> manufacturers, List<MetaContainerRest> categories, List<MetaContainerRest> items) {
        this.brands = brands;
        this.manufacturers = manufacturers;
        this.categories = categories;
        this.items = items;
    }

    public List<MetaContainerRest> getBrands() {
        return brands;
    }

    public void setBrands(List<MetaContainerRest> brands) {
        this.brands = brands;
    }

    public List<MetaContainerRest> getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(List<MetaContainerRest> manufacturers) {
        this.manufacturers = manufacturers;
    }

    public List<MetaContainerRest> getCategories() {
        return categories;
    }

    public void setCategories(List<MetaContainerRest> categories) {
        this.categories = categories;
    }

    public List<MetaContainerRest> getItems() {
        return items;
    }

    public void setItems(List<MetaContainerRest> items) {
        this.items = items;
    }
}
