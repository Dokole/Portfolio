package com.transcacheservice.cacheapp.restController;

import com.transcacheservice.cacheapp.dto.rest.BrandRest;
import com.transcacheservice.cacheapp.dto.rest.CategoryRest;
import com.transcacheservice.cacheapp.dto.rest.ItemRest;
import com.transcacheservice.cacheapp.dto.rest.ManufacturerRest;
import com.transcacheservice.cacheapp.dto.rest.createRestDto.ItemCreateRest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import java.util.List;

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
