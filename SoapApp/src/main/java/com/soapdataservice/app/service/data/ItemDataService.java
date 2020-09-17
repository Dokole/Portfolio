package com.soapdataservice.app.service.data;


import com.soapdataservice.app.domain.Item;
import com.soapdataservice.app.domain.MetadataContainer;

import java.util.List;
import java.util.Set;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public interface ItemDataService {
    Set<Item> findAll();

    Item findById(Long id);

    Item findByName(String name);

    Item save(Item item);

    boolean deleteById(Long id);

    boolean existsById(Long id);

    Set<Item> findAllByBrandDetailsId(Long id);

    Set<Item> findAllByManufacturerId(Long id);

    boolean existsByName(String name);

    List<MetadataContainer> findAllIdsAndNames();

    Set<Item> findAllByBrandDetailsName(String name);

    Set<Item> findAllByManufacturerName(String name);

    Set<Item> findAllByCategoriesId(Long id);

    Set<Item> findAllByCategoriesName(String name);

    Set<Item> findAllByDescriptionLike(String desc);

    Set<Item> findAllByPriceBetween(int low, int high);
}
