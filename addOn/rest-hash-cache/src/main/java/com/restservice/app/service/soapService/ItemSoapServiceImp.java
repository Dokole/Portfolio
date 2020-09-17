package com.restservice.app.service.soapService;

import com.restservice.app.dto.database.ItemDto;
import com.restservice.app.repository.soapRepository.ItemSoapRepository;
import com.restservice.app.domain.cache.redis.ItemCache;
import com.restservice.app.util.CastDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final ItemSoapRepository itemSoapRepository;
    private final CastDto castDto;

    @Autowired
    public ItemSoapServiceImp(ItemSoapRepository itemSoapRepository, CastDto castDto) {
        this.itemSoapRepository = itemSoapRepository;
        this.castDto = castDto;
    }

    @Override
    public ItemCache getItemById(Long id) {
        return castDto.itemFromItemDto(itemSoapRepository.getItemById(id));
    }

    @Override
    public ItemCache createItem(ItemCache item) {
        ItemDto itemDto = castDto.itemDtoFromItem(item);
        itemDto = itemSoapRepository.createItem(itemDto);
        return castDto.itemFromItemDto(itemDto);
    }

    @Override
    public ItemCache updateItem(ItemCache item) {
        ItemDto itemDto = castDto.itemDtoFromItem(item);
        itemDto = itemSoapRepository.updateItem(itemDto);
        return castDto.itemFromItemDto(itemDto);
    }

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

    @Override
    public ItemCache getItemByName(String name) {
        return castDto.itemFromItemDto(itemSoapRepository.getItemByName(name));
    }

    @Override
    @Deprecated
    public List<ItemCache> getItemsByBrandId(Long id) {
        List<ItemDto> itemDtos = itemSoapRepository.getItemsByBrandId(id);
        List<ItemCache> items = new LinkedList<>();
        itemDtos.forEach(i -> items.add(castDto.itemFromItemDto(i)));
        return items;
    }

    @Override
    @Deprecated
    public List<ItemCache> getItemsByManufacturerId(Long id) {
        List<ItemDto> itemDtos = itemSoapRepository.getItemsByManufacturerId(id);
        List<ItemCache> items = new LinkedList<>();
        itemDtos.forEach(i -> items.add(castDto.itemFromItemDto(i)));
        return items;
    }

    @Override
    public List<ItemCache> getItemsByManufacturerName(String name) {
        List<ItemDto> itemDtos = itemSoapRepository.getItemsByManufacturerName(name);
        List<ItemCache> items = new LinkedList<>();
        itemDtos.forEach(i -> items.add(castDto.itemFromItemDto(i)));
        return items;
    }

    @Override
    public List<ItemCache> getItemsByCategoriesId(Long id) {
        List<ItemDto> itemDtos = itemSoapRepository.getItemsByCategoriesId(id);
        List<ItemCache> items = new LinkedList<>();
        itemDtos.forEach(i -> items.add(castDto.itemFromItemDto(i)));
        return items;
    }

    @Override
    public List<ItemCache> getItemsByCategoriesName(String name) {
        List<ItemDto> itemDtos = itemSoapRepository.getItemsByCategoriesName(name);
        List<ItemCache> items = new LinkedList<>();
        itemDtos.forEach(i -> items.add(castDto.itemFromItemDto(i)));
        return items;
    }

    @Override
    public List<ItemCache> getItemsByDescriptionLike(String desc) {
        List<ItemDto> itemDtos = itemSoapRepository.getItemsByDescriptionLike(desc);
        List<ItemCache> items = new LinkedList<>();
        itemDtos.forEach(i -> items.add(castDto.itemFromItemDto(i)));
        return items;
    }

    @Override
    public List<ItemCache> getItemsByPriceBetween(int low, int high) {
        List<ItemDto> itemDtos = itemSoapRepository.getItemsByPriceBetween(low, high);
        List<ItemCache> items = new LinkedList<>();
        itemDtos.forEach(i -> items.add(castDto.itemFromItemDto(i)));
        return items;
    }

    @Override
    public List<ItemCache> getItemsByBrandName(String name) {
        List<ItemDto> itemDtos = itemSoapRepository.getItemsByBrandName(name);
        List<ItemCache> items = new LinkedList<>();
        itemDtos.forEach(i -> items.add(castDto.itemFromItemDto(i)));
        return items;
    }
}
