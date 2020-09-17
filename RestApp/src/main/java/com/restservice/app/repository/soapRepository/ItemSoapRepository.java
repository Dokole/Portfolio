package com.restservice.app.repository.soapRepository;

import com.restservice.app.dto.database.ItemDto;


import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public interface ItemSoapRepository {

    ItemDto getItemById(Long id);

    ItemDto getItemByName(String name);

    List<ItemDto> getAllItems();

    ItemDto createItem(ItemDto item);

    ItemDto updateItem(ItemDto item);

    boolean deleteItemById(Long id);

    List<ItemDto> getItemsByBrandId(Long id);

    List<ItemDto> getItemsByManufacturerId(Long id);

    List<ItemDto> getItemsByBrandName(String name);

    List<ItemDto> getItemsByManufacturerName(String name);

    List<ItemDto> getItemsByCategoriesId(Long id);

    List<ItemDto> getItemsByCategoriesName(String name);

    List<ItemDto> getItemsByDescriptionLike(String desc);

    List<ItemDto> getItemsByPriceBetween(int low, int high);

}
