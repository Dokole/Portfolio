package com.restservice.app.service.databaseService;

import com.restservice.app.exceptions.BadRequestException;
import com.restservice.app.exceptions.NotFoundException;
import com.restservice.app.domain.cache.redis.containers.ItemsCache;
import com.restservice.app.domain.cache.redis.ItemCache;
import com.restservice.app.service.cacheService.ItemCacheService;
import com.restservice.app.service.soapService.ItemSoapService;
import com.restservice.app.util.CacheIdNamer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Service
public class ItemDatabaseServiceImp implements ItemDatabaseService {

    private final Logger logger = LoggerFactory.getLogger(ItemDatabaseServiceImp.class);

    private final ItemCacheService itemCacheService;
    private final ItemSoapService itemSoapService;
    private final CacheIdNamer cacheIdNamer;

    @Autowired
    public ItemDatabaseServiceImp(ItemCacheService itemCacheService, ItemSoapService itemSoapService, CacheIdNamer cacheIdNamer) {
        this.itemCacheService = itemCacheService;
        this.itemSoapService = itemSoapService;
        this.cacheIdNamer = cacheIdNamer;
    }

    @Override
    public ItemCache getItemById(String id) {
        try {
            ItemsCache items = itemCacheService.findCached(cacheIdNamer.getNameOfExecutableMethod() + id);
            if (items == null) {
                items = new ItemsCache();
                items.getItems().add(itemSoapService.getItemById(Long.parseLong(id)));
                items.setId(cacheIdNamer.getNameOfExecutableMethod() + id);
                itemCacheService.save(items);
            }
            return items.getItems().get(0);
        } catch (NumberFormatException e) {
            logger.error("Illegal URI argument = " + id + " Message: " + e.getLocalizedMessage());
            throw new BadRequestException("Illegal URI argument = " + id);
        }
    }

    @Override
    public ItemCache getItemByName(String name) {
        ItemsCache items = itemCacheService.findCached(cacheIdNamer.getNameOfExecutableMethod() + name);
        if (items == null) {
            items = new ItemsCache();
            items.getItems().add(itemSoapService.getItemByName(name));
            items.setId(cacheIdNamer.getNameOfExecutableMethod() + name);
            itemCacheService.save(items);
        }
        return items.getItems().get(0);
    }

    @Override
    public ItemCache createItem(ItemCache item) {

        itemCacheService.invalidateRelatedCaches();
        ItemCache createdItem = itemSoapService.createItem(item);
        itemCacheService.invalidateRelatedCaches();

        return createdItem;
    }

    @Override
    public ItemCache updateItem(ItemCache item) {

        itemCacheService.invalidateRelatedCaches();
        ItemCache updatedItem = itemSoapService.updateItem(item);
        itemCacheService.invalidateRelatedCachesDelay();

        return updatedItem;
    }

    @Override
    public boolean deleteItemById(String id) {
        itemCacheService.invalidateRelatedCaches();
        try {
            if (itemSoapService.deleteItemById(Long.parseLong(id))) {
                itemCacheService.invalidateRelatedCachesDelay();
                return true;
            } else return false;
        } catch (NumberFormatException e) {
            logger.error("Illegal URI argument = " + id + " Message: " + e.getLocalizedMessage());
            throw new BadRequestException("Illegal URI argument = " + id);
        }
    }

    @Override
    public List<ItemCache> getAllItems() {
        List<ItemCache> results = itemSoapService.getAllItems();
        if(results.isEmpty()) {
            throw new NotFoundException("No items found");
        }
        return results;
    }


    @Override
    public List<ItemCache> getAllItemsByBrandDetailsId(String id) {
        ItemsCache itemsCache = itemCacheService.findCached(cacheIdNamer.getNameOfExecutableMethod() + id);
        if (itemsCache == null) {
            itemsCache = new ItemsCache();
            itemsCache.setItems(itemSoapService.getItemsByBrandId(Long.parseLong(id)));
            itemsCache.setId(cacheIdNamer.getNameOfExecutableMethod() + id);
            itemCacheService.save(itemsCache);
        }
        return itemsCache.getItems();
    }

    @Override
    public List<ItemCache> getAllItemsByManufacturerId(String id) {
        ItemsCache itemsCache = itemCacheService.findCached(cacheIdNamer.getNameOfExecutableMethod() + id);
        if (itemsCache == null) {
            itemsCache = new ItemsCache();
            itemsCache.setItems(itemSoapService.getItemsByManufacturerId(Long.parseLong(id)));
            itemsCache.setId(cacheIdNamer.getNameOfExecutableMethod() + id);
            itemCacheService.save(itemsCache);
        }
        return itemsCache.getItems();
    }

    @Override
    public List<ItemCache> getAllItemsByBrandDetailsName(String name) {
        ItemsCache itemsCache = itemCacheService.findCached(cacheIdNamer.getNameOfExecutableMethod() + name);
        if (itemsCache == null) {
            itemsCache = new ItemsCache();
            itemsCache.setItems(itemSoapService.getItemsByBrandName(name));
            itemsCache.setId(cacheIdNamer.getNameOfExecutableMethod() + name);
            itemCacheService.save(itemsCache);
        }
        return itemsCache.getItems();
    }

    @Override
    public List<ItemCache> getAllItemsByManufacturerName(String name) {
        ItemsCache itemsCache = itemCacheService.findCached(cacheIdNamer.getNameOfExecutableMethod() + name);
        if (itemsCache == null) {
            itemsCache = new ItemsCache();
            itemsCache.setItems(itemSoapService.getItemsByManufacturerName(name));
            itemsCache.setId(cacheIdNamer.getNameOfExecutableMethod() + name);
            itemCacheService.save(itemsCache);
        }
        return itemsCache.getItems();
    }

    @Override
    public List<ItemCache> getAllItemsByCategoriesId(String id) {
        ItemsCache itemsCache = itemCacheService.findCached(cacheIdNamer.getNameOfExecutableMethod() + id);
        if (itemsCache == null) {
            itemsCache = new ItemsCache();
            itemsCache.setItems(itemSoapService.getItemsByCategoriesId(Long.parseLong(id)));
            itemsCache.setId(cacheIdNamer.getNameOfExecutableMethod() + id);
            itemCacheService.save(itemsCache);
        }
        return itemsCache.getItems();
    }

    @Override
    public List<ItemCache> getAllItemsByCategoriesName(String name) {
        ItemsCache itemsCache = itemCacheService.findCached(cacheIdNamer.getNameOfExecutableMethod() + name);
        if (itemsCache == null) {
            itemsCache = new ItemsCache();
            itemsCache.setItems(itemSoapService.getItemsByCategoriesName(name));
            itemsCache.setId(cacheIdNamer.getNameOfExecutableMethod() + name);
            itemCacheService.save(itemsCache);
        }
        return itemsCache.getItems();
    }

    //UNCACHED
    @Override
    public List<ItemCache> getItemsByDescriptionLike(String desc) {
        List<ItemCache> results = itemSoapService.getItemsByDescriptionLike(desc);
        if(results.isEmpty()) {
            throw new NotFoundException("No items found by description= " + desc);
        }
        return results;
    }

    //UNCACHED
    @Override
    public List<ItemCache> getItemsByPriceBetween(int low, int high) {
        List<ItemCache> results = itemSoapService.getItemsByPriceBetween(low, high);
        if(results.isEmpty()) {
            throw new NotFoundException("No items found by price between " + low + " and " + high);
        }
        return results;
    }
}
