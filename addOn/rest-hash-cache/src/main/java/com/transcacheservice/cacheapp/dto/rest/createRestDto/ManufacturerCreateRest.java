package com.transcacheservice.cacheapp.dto.rest.createRestDto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.transcacheservice.cacheapp.dto.rest.BaseObjectRest;
import com.transcacheservice.cacheapp.dto.rest.AddressRest;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ManufacturerCreateRest extends BaseObjectRest {

    protected String name;

    protected AddressRest address;

    protected List<Long> brandsIds = new LinkedList<>();

    public ManufacturerCreateRest() {
    }

    @JsonCreator
    public ManufacturerCreateRest(@JsonProperty("id") String id, @JsonProperty("version") Long version,
                           @JsonProperty("name") String name, @JsonProperty("address") AddressRest address,
                           @JsonProperty("brandsIds") List<Long> brandsIds) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.address = address;
        this.brandsIds = brandsIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressRest getAddress() {
        return address;
    }

    public void setAddress(AddressRest address) {
        this.address = address;
    }

    public List<Long> getBrandsIds() {
        return brandsIds;
    }

    public void setBrandsIds(List<Long> brandsIds) {
        this.brandsIds = brandsIds;
    }
}
