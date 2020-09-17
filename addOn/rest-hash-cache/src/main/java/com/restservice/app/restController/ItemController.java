package com.restservice.app.restController;

import com.restservice.app.dto.rest.BrandRest;
import com.restservice.app.dto.rest.CategoryRest;
import com.restservice.app.dto.rest.ItemRest;
import com.restservice.app.dto.rest.ManufacturerRest;
import com.restservice.app.dto.rest.createRestDto.ItemCreateRest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public interface ItemController {

    EntityModel<ItemRest> getItemById(String id);
    EntityModel<ItemRest> getItemByName(String name);

    EntityModel<ItemRest> createItem(ItemCreateRest itemCreateRest);
    EntityModel<ItemRest> updateItem(ItemCreateRest itemCreateRest, String id);

    boolean deleteItemById(String id);

    CollectionModel<EntityModel<ItemRest>> getAllItems();

    EntityModel<BrandRest> getBrandByItemId(String id);
    EntityModel<BrandRest> getBrandByItemName(String name);

    EntityModel<ManufacturerRest> getManufacturerByItemId(String id);
    EntityModel<ManufacturerRest> getManufacturerByItemName(String name);

    CollectionModel<EntityModel<CategoryRest>> getAllCategoriesByItemId(String id);
    CollectionModel<EntityModel<CategoryRest>> getAllCategoriesByItemName(String name);

    CollectionModel<EntityModel<ItemRest>> getItemsByDescription(String desc);

    CollectionModel<EntityModel<ItemRest>> getItemsByPriceBetween(int low, int high);
}
