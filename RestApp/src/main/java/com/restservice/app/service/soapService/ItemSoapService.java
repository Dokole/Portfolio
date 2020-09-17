package com.restservice.app.service.soapService;


import com.restservice.app.domain.cache.redis.ItemCache;

import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public interface ItemSoapService {
    ItemCache getItemById(Long id);

    ItemCache getItemByName(String name);

    List<ItemCache> getItemsByBrandId(Long id);

    List<ItemCache> getItemsByManufacturerId(Long id);

    ItemCache createItem(ItemCache item);

    ItemCache updateItem(ItemCache item);

    boolean deleteItemById(Long id);

    List<ItemCache> getAllItems();

    List<ItemCache> getItemsByManufacturerName(String name);

    List<ItemCache> getItemsByBrandName(String name);

    List<ItemCache> getItemsByCategoriesId(Long id);

    List<ItemCache> getItemsByCategoriesName(String name);

    List<ItemCache> getItemsByDescriptionLike(String desc);

    List<ItemCache> getItemsByPriceBetween(int low, int high);

}
