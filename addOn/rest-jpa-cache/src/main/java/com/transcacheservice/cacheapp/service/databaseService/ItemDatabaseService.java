package com.transcacheservice.cacheapp.service.databaseService;

import com.transcacheservice.cacheapp.domain.cache.redis.ItemCache;

import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */


public interface ItemDatabaseService {
    ItemCache getItemById(String id);

    ItemCache createItem(ItemCache item);

    ItemCache updateItem(ItemCache item);

    boolean deleteItemById(String id);

    List<ItemCache> getAllItems();

    ItemCache getItemByName(String name);

    List<ItemCache> getAllItemsByBrandDetailsId(String id);

    List<ItemCache> getAllItemsByManufacturerId(String id);

    List<ItemCache> getAllItemsByBrandDetailsName(String name);

    List<ItemCache> getAllItemsByManufacturerName(String name);

    List<ItemCache> getAllItemsByCategoriesId(String id);

    List<ItemCache> getAllItemsByCategoriesName(String name);

    List<ItemCache> getItemsByDescriptionLike(String desc);

    List<ItemCache> getItemsByPriceBetween(int low, int high);

}
