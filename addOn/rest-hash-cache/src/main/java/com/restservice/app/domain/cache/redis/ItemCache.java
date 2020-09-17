package com.restservice.app.domain.cache.redis;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public class ItemCache extends AbstractCacheObject {
    protected String description;
    protected Integer price;
    protected BrandCache brand;
    protected ManufacturerCache manufacturer;
    protected List<CategoryCache> categories = new ArrayList<>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public BrandCache getBrand() {
        return brand;
    }

    public void setBrand(BrandCache brand) {
        this.brand = brand;
    }

    public ManufacturerCache getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(ManufacturerCache manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<CategoryCache> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryCache> categories) {
        this.categories = categories;
    }
}
