package com.transcacheservice.cacheapp.dto.rest.createRestDto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.transcacheservice.cacheapp.dto.rest.AddressRest;
import com.transcacheservice.cacheapp.dto.rest.BaseObjectRest;
import liquibase.pro.packaged.A;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemCreateRest extends BaseObjectRest {

    protected String name;

    protected String description;

    protected int price;

    protected Long brandId;

    protected Long manufacturerId;

    protected List<Long> categoriesIds = new ArrayList<>();

    public ItemCreateRest() {
    }

    @JsonCreator
    public ItemCreateRest(@JsonProperty("id") String id, @JsonProperty("version") Long version,
                          @JsonProperty("name") String name, @JsonProperty("description") String description,
                          @JsonProperty("price") int price, @JsonProperty("brandId") Long brandId,
                          @JsonProperty("manufacturerId") Long manufacturerId, @JsonProperty("categoriesIds") List<Long> categoriesIds) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.description = description;
        this.price = price;
        this.brandId = brandId;
        this.manufacturerId = manufacturerId;
        this.categoriesIds = categoriesIds;
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

    public Long getBrandDetailsId() {
        return brandId;
    }

    public void setBrandDetailsId(Long brandDetailsId) {
        this.brandId = brandDetailsId;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public List<Long> getCategories() {
        return categoriesIds;
    }

    public void setCategories(List<Long> categories) {
        this.categoriesIds = categories;
    }
}
