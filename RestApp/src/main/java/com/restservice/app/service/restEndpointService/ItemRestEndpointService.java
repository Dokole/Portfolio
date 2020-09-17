package com.restservice.app.service.restEndpointService;

import com.restservice.app.dto.rest.BrandRest;
import com.restservice.app.dto.rest.CategoryRest;
import com.restservice.app.dto.rest.ItemRest;
import com.restservice.app.dto.rest.ManufacturerRest;
import com.restservice.app.dto.rest.createRestDto.ItemCreateRest;
import org.springframework.hateoas.EntityModel;

import java.util.List;


/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public interface ItemRestEndpointService {

    EntityModel<ItemRest> getItemById(String id);

    EntityModel<ItemRest> getItemByName(String name);

    EntityModel<ItemRest> createItem(ItemCreateRest itemCreateRest);

    EntityModel<ItemRest> updateItem(ItemCreateRest itemCreateRest);

    boolean deleteItemById(String id);

    List<EntityModel<ItemRest>> getAllItems();

    EntityModel<BrandRest> getBrandByItemId(String id);
    EntityModel<BrandRest> getBrandByItemName(String id);

    EntityModel<ManufacturerRest> getManufacturerByItemId(String id);
    EntityModel<ManufacturerRest> getManufacturerByItemName(String name);

    List<EntityModel<CategoryRest>> getAllCategoriesByItemId(String id);
    List<EntityModel<CategoryRest>> getAllCategoriesByItemName(String name);


    List<EntityModel<ItemRest>> getItemsByDescription(String desc);

    List<EntityModel<ItemRest>> getItemsByPriceBetween(int low, int high);
}
