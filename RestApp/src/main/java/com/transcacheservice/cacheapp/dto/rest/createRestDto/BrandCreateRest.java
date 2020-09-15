package com.transcacheservice.cacheapp.dto.rest.createRestDto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.transcacheservice.cacheapp.dto.rest.BaseObjectRest;
import com.transcacheservice.cacheapp.dto.rest.AddressRest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BrandCreateRest extends BaseObjectRest {

    protected String name;

    protected AddressRest address;

    protected List<Long> manufacturersIds = new ArrayList<>();

    public BrandCreateRest() {
    }

    @JsonCreator
    public BrandCreateRest(@JsonProperty("id") String id,@JsonProperty("version") Long version,
                           @JsonProperty("name") String name, @JsonProperty("address") AddressRest address,
                           @JsonProperty("manufacturersIds") List<Long> manufacturersIds) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.address = address;
        this.manufacturersIds = manufacturersIds;
    }

    public AddressRest getAddress() {
        return address;
    }

    public void setAddress(AddressRest address) {
        this.address = address;
    }

    public List<Long> getManufacturersIds() {
        return manufacturersIds;
    }

    public void setManufacturersIds(List<Long> manufacturers) {
        this.manufacturersIds = manufacturers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
