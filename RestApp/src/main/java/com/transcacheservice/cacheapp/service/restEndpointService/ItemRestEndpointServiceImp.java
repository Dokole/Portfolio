package com.transcacheservice.cacheapp.service.restEndpointService;

import com.transcacheservice.cacheapp.dto.rest.BrandRest;
import com.transcacheservice.cacheapp.dto.rest.CategoryRest;
import com.transcacheservice.cacheapp.dto.rest.ItemRest;
import com.transcacheservice.cacheapp.dto.rest.ManufacturerRest;
import com.transcacheservice.cacheapp.dto.rest.createRestDto.ItemCreateRest;
import com.transcacheservice.cacheapp.domain.cache.redis.BrandCache;
import com.transcacheservice.cacheapp.domain.cache.redis.CategoryCache;
import com.transcacheservice.cacheapp.domain.cache.redis.ItemCache;
import com.transcacheservice.cacheapp.domain.cache.redis.ManufacturerCache;
import com.transcacheservice.cacheapp.exceptions.BadRequestException;
import com.transcacheservice.cacheapp.service.soapService.BrandSoapService;
import com.transcacheservice.cacheapp.service.soapService.CategorySoapService;
import com.transcacheservice.cacheapp.service.soapService.ItemSoapService;
import com.transcacheservice.cacheapp.service.soapService.ManufacturerSoapService;
import com.transcacheservice.cacheapp.util.CastRestCreate;
import com.transcacheservice.cacheapp.util.ModelAssemblers.RestModelAssembler;
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

    private final ItemSoapService itemSoapService;
    private final CategorySoapService categorySoapService;
    private final BrandSoapService brandSoapService;
    private final ManufacturerSoapService manufacturerSoapService;
    private final RestModelAssembler restModelAssembler;
    private final CastRestCreate castRestCreate;

    @Autowired
    public ItemRestEndpointServiceImp(ItemSoapService itemSoapService, CategorySoapService categorySoapService, BrandSoapService brandSoapService, ManufacturerSoapService manufacturerSoapService, RestModelAssembler restModelAssembler, CastRestCreate castRestCreate) {
        this.itemSoapService = itemSoapService;
        this.categorySoapService = categorySoapService;
        this.brandSoapService = brandSoapService;
        this.manufacturerSoapService = manufacturerSoapService;
        this.restModelAssembler = restModelAssembler;
        this.castRestCreate = castRestCreate;
    }

    @Override
    public EntityModel<ItemRest> getItemById(String id) {
        try {
            ItemCache item = itemSoapService.getItemById(Long.parseLong(id));
            return restModelAssembler.itemToItemRestModel(item);
        } catch (NumberFormatException e) {
            logger.error("Illegal URI argument = " + id + " Message: " + e.getLocalizedMessage());
            throw new BadRequestException("Illegal URI argument = " + id);
        }
    }

    @Override
    public EntityModel<ItemRest> getItemByName(String name) {
        ItemCache item = itemSoapService.getItemByName(name);
        return restModelAssembler.itemToItemRestModel(item);
    }

    @Override
    public EntityModel<ItemRest> createItem(ItemCreateRest itemCreateRest) {
        ItemCache item = castRestCreate.toItem(itemCreateRest);
        item = itemSoapService.createItem(item);
        return restModelAssembler.itemToItemRestModel(item);
    }

    @Override
    public EntityModel<ItemRest> updateItem(ItemCreateRest itemCreateRest) {
        ItemCache item = castRestCreate.toItem(itemCreateRest);
        item = itemSoapService.updateItem(item);
        return restModelAssembler.itemToItemRestModel(item);
    }

    @Override
    public boolean deleteItemById(String id) {
        try {
            return itemSoapService.deleteItemById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            logger.error("Illegal URI argument = " + id + " Message: " + e.getLocalizedMessage());
            throw new BadRequestException("Illegal URI argument = " + id);
        }
    }

    @Override
    public List<EntityModel<ItemRest>> getAllItems() {
        List<ItemCache> results = itemSoapService.getAllItems();
        List<EntityModel<ItemRest>> itemsRest = new LinkedList<>();
        results.forEach(item -> itemsRest.add(restModelAssembler.itemToItemRestModel(item)));
        return itemsRest;
    }

    @Override
    public EntityModel<BrandRest> getBrandByItemId(String id) {
        try {
            BrandCache brand = brandSoapService.getBrandByItemId(Long.parseLong(id));
            return restModelAssembler.brandToBrandRestModel(brand);
        } catch (NumberFormatException e) {
            logger.error("Illegal URI argument = " + id + " Message: " + e.getLocalizedMessage());
            throw new BadRequestException("Illegal URI argument = " + id);
        }
    }

    @Override
    public EntityModel<BrandRest> getBrandByItemName(String name) {
        BrandCache brand = brandSoapService.getBrandByItemName(name);
        return restModelAssembler.brandToBrandRestModel(brand);
    }

    @Override
    public EntityModel<ManufacturerRest> getManufacturerByItemId(String id) {
        try {
            ManufacturerCache manufacturer = manufacturerSoapService.getManufacturerByItemId(Long.parseLong(id));
            return restModelAssembler.manufacturerToManufacturerRestModel(manufacturer);
        } catch (NumberFormatException e) {
            logger.error("Illegal URI argument = " + id + " Message: " + e.getLocalizedMessage());
            throw new BadRequestException("Illegal URI argument = " + id);
        }
    }

    @Override
    public EntityModel<ManufacturerRest> getManufacturerByItemName(String name) {
        ManufacturerCache manufacturer = manufacturerSoapService.getManufacturerByItemName(name);
        return restModelAssembler.manufacturerToManufacturerRestModel(manufacturer);
    }

    @Override
    public List<EntityModel<CategoryRest>> getAllCategoriesByItemId(String id) {
        try {
            List<CategoryCache> categories = categorySoapService.getCategoriesByItemsId(Long.parseLong(id));
            List<EntityModel<CategoryRest>> categoriesRest = new LinkedList<>();
            categories.forEach(c -> categoriesRest.add(restModelAssembler.categoryToCategoryRestModel(c)));
            return categoriesRest;
        } catch (NumberFormatException e) {
            logger.error("Illegal URI argument = " + id + " Message: " + e.getLocalizedMessage());
            throw new BadRequestException("Illegal URI argument = " + id);
        }

    }
    @Override
    public List<EntityModel<CategoryRest>> getAllCategoriesByItemName(String name) {
        List<CategoryCache> categories = categorySoapService.getCategoriesByItemsName(name);
        List<EntityModel<CategoryRest>> categoriesRest = new LinkedList<>();
        categories.forEach(c -> categoriesRest.add(restModelAssembler.categoryToCategoryRestModel(c)));
        return categoriesRest;
    }

    @Override
    public List<EntityModel<ItemRest>> getItemsByDescription(String desc) {
        List<ItemCache> results = itemSoapService.getItemsByDescriptionLike(desc);
        List<EntityModel<ItemRest>> itemsRest = new LinkedList<>();
        results.forEach(item -> itemsRest.add(restModelAssembler.itemToItemRestModel(item)));
        return itemsRest;
    }

    @Override
    public List<EntityModel<ItemRest>> getItemsByPriceBetween(int low, int high) {
        List<ItemCache> results = itemSoapService.getItemsByPriceBetween(low, high);
        List<EntityModel<ItemRest>> itemsRest = new LinkedList<>();
        results.forEach(item -> itemsRest.add(restModelAssembler.itemToItemRestModel(item)));
        return itemsRest;
    }
}
