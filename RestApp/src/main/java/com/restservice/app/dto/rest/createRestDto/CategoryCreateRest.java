package com.restservice.app.dto.rest.createRestDto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.restservice.app.dto.rest.BaseObjectRest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryCreateRest extends BaseObjectRest {

    protected String name;

    protected List<Long> itemsIds = new ArrayList<>();

    public CategoryCreateRest() {
    }

    @JsonCreator
    public CategoryCreateRest(@JsonProperty("id") String id, @JsonProperty("version") Long version,
                           @JsonProperty("name") String name, @JsonProperty("itemsIds") List<Long> itemsIds) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.itemsIds = itemsIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getItemsIds() {
        return itemsIds;
    }

    public void setItemsIds(List<Long> itemsIds) {
        this.itemsIds = itemsIds;
    }
}
