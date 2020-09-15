package com.transcacheservice.cacheapp.service.soapService;

import com.transcacheservice.cacheapp.dto.database.ItemDto;
import com.transcacheservice.cacheapp.domain.cache.redis.ItemCache;
import com.transcacheservice.cacheapp.repository.soapRepository.ItemSoapRepository;
import com.transcacheservice.cacheapp.util.CastDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Service
public class ItemSoapServiceImp implements ItemSoapService {

    private final Logger logger = LoggerFactory.getLogger(ItemSoapServiceImp.class);

    private final String cacheName = "Item";

    private final ItemSoapRepository itemSoapRepository;
    private final CastDto castDto;

    @Autowired
    public ItemSoapServiceImp(ItemSoapRepository itemSoapRepository, CastDto castDto) {
        this.itemSoapRepository = itemSoapRepository;
        this.castDto = castDto;
    }

    @Cacheable(value = cacheName, key = "@cacheIdNamer.nameOfExecutableMethod.concat(#id)")
    @Override
    public ItemCache getItemById(Long id) {
        return castDto.itemFromItemDto(itemSoapRepository.getItemById(id));
    }

    @CacheEvict(cacheNames = {"Brand", "Item", "Manufacturer", "Category"})
    @Override
    public ItemCache createItem(ItemCache item) {
        ItemDto itemDto = castDto.itemDtoFromItem(item);
        itemDto = itemSoapRepository.createItem(itemDto);
        return castDto.itemFromItemDto(itemDto);
    }

    @CacheEvict(cacheNames = {"Brand", "Item", "Manufacturer", "Category"})
    @Override
    public ItemCache updateItem(ItemCache item) {
        ItemDto itemDto = castDto.itemDtoFromItem(item);
        itemDto = itemSoapRepository.updateItem(itemDto);
        return castDto.itemFromItemDto(itemDto);
    }

    @CacheEvict(cacheNames = {"Brand", "Item", "Manufacturer", "Category"})
    @Override
    public boolean deleteItemById(Long id) {
        return itemSoapRepository.deleteItemById(id);

    }

    @Override
    public List<ItemCache> getAllItems() {
        List<ItemDto> itemDtos = itemSoapRepository.getAllItems();
        List<ItemCache> items = new LinkedList<>();
        itemDtos.forEach(i -> items.add(castDto.itemFromItemDto(i)));
        return items;
    }

    @Cacheable(value = cacheName, key = "@cacheIdNamer.nameOfExecutableMethod.concat(#name)")
    @Override
    public ItemCache getItemByName(String name) {
        return castDto.itemFromItemDto(itemSoapRepository.getItemByName(name));
    }

    @Cacheable(value = cacheName, key = "@cacheIdNamer.nameOfExecutableMethod.concat(#id)")
    @Override
    public List<ItemCache> getItemsByBrandId(Long id) {
        List<ItemDto> itemDtos = itemSoapRepository.getItemsByBrandId(id);
        List<ItemCache> items = new LinkedList<>();
        itemDtos.forEach(i -> items.add(castDto.itemFromItemDto(i)));
        return items;
    }

    @Cacheable(value = cacheName, key = "@cacheIdNamer.nameOfExecutableMethod.concat(#id)")
    @Override
    public List<ItemCache> getItemsByManufacturerId(Long id) {
        List<ItemDto> itemDtos = itemSoapRepository.getItemsByManufacturerId(id);
        List<ItemCache> items = new LinkedList<>();
        itemDtos.forEach(i -> items.add(castDto.itemFromItemDto(i)));
        return items;
    }

    @Cacheable(value = cacheName, key = "@cacheIdNamer.nameOfExecutableMethod.concat(#name)")
    @Override
    public List<ItemCache> getItemsByManufacturerName(String name) {
        List<ItemDto> itemDtos = itemSoapRepository.getItemsByManufacturerName(name);
        List<ItemCache> items = new LinkedList<>();
        itemDtos.forEach(i -> items.add(castDto.itemFromItemDto(i)));
        return items;
    }

    @Cacheable(value = cacheName, key = "@cacheIdNamer.nameOfExecutableMethod.concat(#id)")
    @Override
    public List<ItemCache> getItemsByCategoriesId(Long id) {
        List<ItemDto> itemDtos = itemSoapRepository.getItemsByCategoriesId(id);
        List<ItemCache> items = new LinkedList<>();
        itemDtos.forEach(i -> items.add(castDto.itemFromItemDto(i)));
        return items;
    }

    @Cacheable(value = cacheName, key = "@cacheIdNamer.nameOfExecutableMethod.concat(#name)")
    @Override
    public List<ItemCache> getItemsByCategoriesName(String name) {
        List<ItemDto> itemDtos = itemSoapRepository.getItemsByCategoriesName(name);
        List<ItemCache> items = new LinkedList<>();
        itemDtos.forEach(i -> items.add(castDto.itemFromItemDto(i)));
        return items;
    }

    @Cacheable(value = cacheName, key = "@cacheIdNamer.nameOfExecutableMethod.concat(#desc)")
    @Override
    public List<ItemCache> getItemsByDescriptionLike(String desc) {
        List<ItemDto> itemDtos = itemSoapRepository.getItemsByDescriptionLike(desc);
        List<ItemCache> items = new LinkedList<>();
        itemDtos.forEach(i -> items.add(castDto.itemFromItemDto(i)));
        return items;
    }

    @Cacheable(value = cacheName, key = "@cacheIdNamer.nameOfExecutableMethod.concat(#low + #high)")
    @Override
    public List<ItemCache> getItemsByPriceBetween(int low, int high) {
        List<ItemDto> itemDtos = itemSoapRepository.getItemsByPriceBetween(low, high);
        List<ItemCache> items = new LinkedList<>();
        itemDtos.forEach(i -> items.add(castDto.itemFromItemDto(i)));
        return items;
    }

    @Cacheable(value = cacheName, key = "@cacheIdNamer.nameOfExecutableMethod.concat(#name)")
    @Override
    public List<ItemCache> getItemsByBrandName(String name) {
        List<ItemDto> itemDtos = itemSoapRepository.getItemsByBrandName(name);
        List<ItemCache> items = new LinkedList<>();
        itemDtos.forEach(i -> items.add(castDto.itemFromItemDto(i)));
        return items;
    }
}
