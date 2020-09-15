package com.producingservice.app.service.endpoint;

import database.dto.app.producingservice.com.ItemDto;

import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public interface ItemEndpointService {

    List<ItemDto> findAll();

    ItemDto findById(Long id);

    ItemDto findByName(String name);

    ItemDto save(ItemDto itemDto);

    ItemDto update(ItemDto itemDto);

    boolean deleteById(Long id);

    boolean existsById(Long id);

    List<ItemDto> findAllByBrandDetailsId(Long id);

    List<ItemDto> findAllByManufacturerId(Long id);

    List<ItemDto> findAllByBrandDetailsName(String name);

    List<ItemDto> findAllByManufacturerName(String name);

    List<ItemDto> findAllByCategoriesId(Long id);

    List<ItemDto> findAllByCategoriesName(String name);

    List<ItemDto> findAllByDescriptionLike(String desc);

    List<ItemDto> findAllByPriceBetween(int low, int high);
}

