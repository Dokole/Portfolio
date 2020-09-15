package com.transcacheservice.cacheapp.dto;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public class CreationData {

    protected List<MetaContainer> brands = new LinkedList<>();
    protected List<MetaContainer> manufacturers = new LinkedList<>();
    protected List<MetaContainer> categories = new LinkedList<>();
    protected List<MetaContainer> items = new LinkedList<>();

    public CreationData() {
    }

    public CreationData(List<MetaContainer> brands, List<MetaContainer> manufacturers, List<MetaContainer> categories, List<MetaContainer> items) {
        this.brands = brands;
        this.manufacturers = manufacturers;
        this.categories = categories;
        this.items = items;
    }

    public List<MetaContainer> getBrands() {
        return brands;
    }

    public void setBrands(List<MetaContainer> brands) {
        this.brands = brands;
    }

    public List<MetaContainer> getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(List<MetaContainer> manufacturers) {
        this.manufacturers = manufacturers;
    }

    public List<MetaContainer> getCategories() {
        return categories;
    }

    public void setCategories(List<MetaContainer> categories) {
        this.categories = categories;
    }

    public List<MetaContainer> getItems() {
        return items;
    }

    public void setItems(List<MetaContainer> items) {
        this.items = items;
    }
}

