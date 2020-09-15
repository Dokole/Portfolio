package com.producingservice.app.service.endpoint;

import com.producingservice.app.domain.Item;
import com.producingservice.app.exceptions.BadRequestException;
import com.producingservice.app.exceptions.NotFoundException;
import com.producingservice.app.service.data.ItemDataService;
import com.producingservice.app.util.CastToDtoUtil;
import database.dto.app.producingservice.com.ItemDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Service("itemEndpointService")
public class ItemEndpointServiceImp implements ItemEndpointService {

    private final Logger logger = LoggerFactory.getLogger(ItemEndpointServiceImp.class);

    private final ItemDataService itemDataService;
    private final CastToDtoUtil castToDtoUtil;

    @Autowired
    public ItemEndpointServiceImp(ItemDataService itemDataService, CastToDtoUtil castToDtoUtil) {
        this.itemDataService = itemDataService;
        this.castToDtoUtil = castToDtoUtil;
    }

    @Override
    public List<ItemDto> findAll() {
        Set<Item> items = itemDataService.findAll();
        List<ItemDto> itemsDto = new LinkedList<>();
        if (items.isEmpty()) {
            logger.error("IN findAll - No items in the database");
            throw new NotFoundException("No items found in the database.");
        }
        items.forEach(i -> itemsDto.add(castToDtoUtil.itemToItemDto(i)));
        return itemsDto;
    }

    @Override
    public ItemDto findById(Long id) {
        Item item = null;
        item = itemDataService.findById(id);

        if (item == null) {
            logger.warn("IN findById - no item found by id={}", id);
            throw new NotFoundException("No item found by id=" + id);
        }
        return castToDtoUtil.itemToItemDto(item);
    }

    @Override
    public ItemDto findByName(String name) {
        Item item = null;
        item = itemDataService.findByName(name);

        if (item == null) {
            logger.warn("IN findByName - no item found by name={}", name);
            throw new NotFoundException("No item found by name=" + name);
        }
        return castToDtoUtil.itemToItemDto(item);
    }

    @Override
    public ItemDto save(ItemDto itemDto) {
        if (itemDto.getId() != null) {
            throw new BadRequestException("Id=" + itemDto.getId() + " should be null to create an item. Can't be saved.");
        }
        if (itemDataService.existsByName(itemDto.getName())) {
            throw new BadRequestException("Item with name=" + itemDto.getName() + " already exists");
        }
        Item item = castToDtoUtil.itemDtoToItem(itemDto);
        item = itemDataService.save(item);

        itemDto = castToDtoUtil.itemToItemDto(item);
        return itemDto;
    }

    @Override
    public ItemDto update(ItemDto itemDto) {
        if (!existsById(itemDto.getId())) {
            throw new BadRequestException("Can't update brand with id=" + itemDto.getId() + ". It doesn't exists");
        }
        Item item = castToDtoUtil.itemDtoToItem(itemDto);
        item = itemDataService.save(item);
        itemDto = castToDtoUtil.itemToItemDto(item);
        return itemDto;
    }

    @Override
    public boolean deleteById(Long id) {
        itemDataService.deleteById(id);
        return true;
    }

    @Override
    public boolean existsById(Long id) {
        return itemDataService.existsById(id);

    }

    @Override
    public List<ItemDto> findAllByBrandDetailsId(Long id) {
        Set<Item> items = itemDataService.findAllByBrandDetailsId(id);
        List<ItemDto> itemsDto = new LinkedList<>();
        if (items.isEmpty()) {
            logger.error("IN findAllByBrandDetailsId - No items in the database by brand's id={}", id);
            throw new NotFoundException("No items found in the database by brand's id=" + id);
        }
        items.forEach(i -> itemsDto.add(castToDtoUtil.itemToItemDto(i)));
        return itemsDto;
    }

    @Override
    public List<ItemDto> findAllByManufacturerId(Long id) {
        Set<Item> items = itemDataService.findAllByManufacturerId(id);
        List<ItemDto> itemsDto = new LinkedList<>();
        if (items.isEmpty()) {
            logger.error("IN findAllByManufacturerId - No items in the database by manufacturer's id={}", id);
            throw new NotFoundException("No items found in the database by manufacturer's id=" + id);
        }
        items.forEach(i -> itemsDto.add(castToDtoUtil.itemToItemDto(i)));
        return itemsDto;
    }

    @Override
    public List<ItemDto> findAllByBrandDetailsName(String name) {
        Set<Item> items = itemDataService.findAllByBrandDetailsName(name);
        List<ItemDto> itemsDto = new LinkedList<>();
        if (items.isEmpty()) {
            logger.error("IN findAllByItemsName - no items found in the database by brand name={}", name);
            throw new NotFoundException("No items found in the database by brand name=" + name);
        }
        items.forEach(i -> itemsDto.add(castToDtoUtil.itemToItemDto(i)));
        return itemsDto;
    }

    @Override
    public List<ItemDto> findAllByManufacturerName(String name) {
        Set<Item> items = itemDataService.findAllByManufacturerName(name);
        List<ItemDto> itemsDto = new LinkedList<>();
        if (items.isEmpty()) {
            logger.error("IN findAllByItemsName - no items found in the database by manufacturer name={}", name);
            throw new NotFoundException("No items found in the database by manufacturer name=" + name);
        }
        items.forEach(i -> itemsDto.add(castToDtoUtil.itemToItemDto(i)));
        return itemsDto;
    }

    @Override
    public List<ItemDto> findAllByCategoriesId(Long id) {
        Set<Item> items = itemDataService.findAllByCategoriesId(id);
        List<ItemDto> itemsDto = new LinkedList<>();
        if (items.isEmpty()) {
            logger.error("IN findAllByItemsName - no items found in the database by category id={}", id);
            throw new NotFoundException("No items found in the database by category id=" + id);
        }
        items.forEach(i -> itemsDto.add(castToDtoUtil.itemToItemDto(i)));
        return itemsDto;
    }

    @Override
    public List<ItemDto> findAllByCategoriesName(String name) {
        Set<Item> items = itemDataService.findAllByCategoriesName(name);
        List<ItemDto> itemsDto = new LinkedList<>();
        if (items.isEmpty()) {
            logger.error("IN findAllByItemsName - no items found in the database by category name={}", name);
            throw new NotFoundException("No items found in the database by category name=" + name);
        }
        items.forEach(i -> itemsDto.add(castToDtoUtil.itemToItemDto(i)));
        return itemsDto;
    }

    @Override
    public List<ItemDto> findAllByDescriptionLike(String desc) {
        Set<Item> items = itemDataService.findAllByDescriptionLike(desc);
        List<ItemDto> itemsDto = new LinkedList<>();
        if (items.isEmpty()) {
            logger.error("IN findAllByItemsName - no items found in the database by description={}", desc);
            throw new NotFoundException("No items found in the database by description=" + desc);
        }
        items.forEach(i -> itemsDto.add(castToDtoUtil.itemToItemDto(i)));
        return itemsDto;
    }

    @Override
    public List<ItemDto> findAllByPriceBetween(int low, int high) {
        Set<Item> items = itemDataService.findAllByPriceBetween(low, high);
        List<ItemDto> itemsDto = new LinkedList<>();
        if (items.isEmpty()) {
            logger.error("IN findAllByItemsName - no items found in the price range of {} and {}", low, high);
            throw new NotFoundException("No items found in the price range of" + low + " and " + high);
        }
        items.forEach(i -> itemsDto.add(castToDtoUtil.itemToItemDto(i)));
        return itemsDto;
    }
}
