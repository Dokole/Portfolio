package com.restservice.app.service.restEndpointService;

import com.restservice.app.dto.rest.BrandRest;
import com.restservice.app.dto.rest.CategoryRest;
import com.restservice.app.dto.rest.ItemRest;
import com.restservice.app.dto.rest.ManufacturerRest;
import com.restservice.app.dto.rest.createRestDto.ItemCreateRest;
import com.restservice.app.domain.cache.redis.BrandCache;
import com.restservice.app.domain.cache.redis.CategoryCache;
import com.restservice.app.domain.cache.redis.ItemCache;
import com.restservice.app.domain.cache.redis.ManufacturerCache;
import com.restservice.app.service.databaseService.BrandDatabaseService;
import com.restservice.app.service.databaseService.CategoryDatabaseService;
import com.restservice.app.service.databaseService.ItemDatabaseService;
import com.restservice.app.service.databaseService.ManufacturerDatabaseService;
import com.restservice.app.util.CastRestCreate;
import com.restservice.app.util.ModelAssemblers.RestModelAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Service
public class ItemRestEndpointServiceImp implements ItemRestEndpointService {
    private final Logger logger = LoggerFactory.getLogger(ItemRestEndpointServiceImp.class);

    private final ItemDatabaseService itemDatabaseService;
    private final CategoryDatabaseService categoryDatabaseService;
    private final BrandDatabaseService brandDatabaseService;
    private final ManufacturerDatabaseService manufacturerDatabaseService;
    private final RestModelAssembler restModelAssembler;
    private final CastRestCreate castRestCreate;

    @Autowired
    public ItemRestEndpointServiceImp(ItemDatabaseService itemDatabaseService, CategoryDatabaseService categoryDatabaseService, BrandDatabaseService brandDatabaseService, ManufacturerDatabaseService manufacturerDatabaseService, RestModelAssembler restModelAssembler, CastRestCreate castRestCreate) {
        this.itemDatabaseService = itemDatabaseService;
        this.categoryDatabaseService = categoryDatabaseService;
        this.brandDatabaseService = brandDatabaseService;
        this.manufacturerDatabaseService = manufacturerDatabaseService;
        this.restModelAssembler = restModelAssembler;
        this.castRestCreate = castRestCreate;
    }

    @Override
    public EntityModel<ItemRest> getItemById(String id) {
        ItemCache item = itemDatabaseService.getItemById(id);
        return restModelAssembler.itemToItemRestModel(item);
    }

    @Override
    public EntityModel<ItemRest> getItemByName(String name) {
        ItemCache item = itemDatabaseService.getItemByName(name);
        return restModelAssembler.itemToItemRestModel(item);
    }

    @Override
    public EntityModel<ItemRest> createItem(ItemCreateRest itemCreateRest) {
        ItemCache item = castRestCreate.toItem(itemCreateRest);
        item = itemDatabaseService.createItem(item);
        return restModelAssembler.itemToItemRestModel(item);
    }

    @Override
    public EntityModel<ItemRest> updateItem(ItemCreateRest itemCreateRest) {
        ItemCache item = castRestCreate.toItem(itemCreateRest);
        item = itemDatabaseService.updateItem(item);
        return restModelAssembler.itemToItemRestModel(item);
    }

    @Override
    public boolean deleteItemById(String id) {
        return itemDatabaseService.deleteItemById(id);
    }

    @Override
    public List<EntityModel<ItemRest>> getAllItems() {
        List<ItemCache> results = itemDatabaseService.getAllItems();
        List<EntityModel<ItemRest>> itemsRest = new LinkedList<>();
        results.forEach(item -> itemsRest.add(restModelAssembler.itemToItemRestModel(item)));
        return itemsRest;
    }

    @Override
    public EntityModel<BrandRest> getBrandByItemId(String id) {
        BrandCache brand = brandDatabaseService.getBrandByItemId(id);
        return restModelAssembler.brandToBrandRestModel(brand);
    }

    @Override
    public EntityModel<BrandRest> getBrandByItemName(String name) {
        BrandCache brand = brandDatabaseService.getBrandByItemName(name);
        return restModelAssembler.brandToBrandRestModel(brand);
    }

    @Override
    public EntityModel<ManufacturerRest> getManufacturerByItemId(String id) {
        ManufacturerCache manufacturer = manufacturerDatabaseService.getManufacturerByItemId(id);
        return restModelAssembler.manufacturerToManufacturerRestModel(manufacturer);
    }

    @Override
    public EntityModel<ManufacturerRest> getManufacturerByItemName(String name) {
        ManufacturerCache manufacturer = manufacturerDatabaseService.getManufacturerByItemName(name);
        return restModelAssembler.manufacturerToManufacturerRestModel(manufacturer);
    }

    @Override
    public List<EntityModel<CategoryRest>> getAllCategoriesByItemId(String id) {
        List<CategoryCache> categories = categoryDatabaseService.getAllCategoriesByItemsId(id);
        List<EntityModel<CategoryRest>> categoriesRest = new LinkedList<>();
        categories.forEach(c -> categoriesRest.add(restModelAssembler.categoryToCategoryRestModel(c)));
        return categoriesRest;
    }
    @Override
    public List<EntityModel<CategoryRest>> getAllCategoriesByItemName(String name) {
        List<CategoryCache> categories = categoryDatabaseService.getAllCategoriesByItemsName(name);
        List<EntityModel<CategoryRest>> categoriesRest = new LinkedList<>();
        categories.forEach(c -> categoriesRest.add(restModelAssembler.categoryToCategoryRestModel(c)));
        return categoriesRest;
    }

    @Override
    public List<EntityModel<ItemRest>> getItemsByDescription(String desc) {
        List<ItemCache> results = itemDatabaseService.getItemsByDescriptionLike(desc);
        List<EntityModel<ItemRest>> itemsRest = new LinkedList<>();
        results.forEach(item -> itemsRest.add(restModelAssembler.itemToItemRestModel(item)));
        return itemsRest;
    }

    @Override
    public List<EntityModel<ItemRest>> getItemsByPriceBetween(int low, int high) {
        List<ItemCache> results = itemDatabaseService.getItemsByPriceBetween(low, high);
        List<EntityModel<ItemRest>> itemsRest = new LinkedList<>();
        results.forEach(item -> itemsRest.add(restModelAssembler.itemToItemRestModel(item)));
        return itemsRest;
    }
}
