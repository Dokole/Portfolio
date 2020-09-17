package com.soapdataservice.app.service.data;

import com.soapdataservice.app.domain.Item;
import com.soapdataservice.app.domain.MetadataContainer;
import com.soapdataservice.app.repository.ItemRepository;
import org.assertj.core.util.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Service("itemDataService")
@Transactional
public class ItemDataServiceImp implements ItemDataService {

    private static final Logger logger = LoggerFactory.getLogger(ItemDataServiceImp.class);
    private final ItemRepository itemRepository;

    @Autowired
    public ItemDataServiceImp(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Item> findAll() {
        return Sets.newHashSet(itemRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Item findById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public boolean deleteById(Long id) {
        itemRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return itemRepository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Item findByName(String name) {
        return itemRepository.findByName(name).orElse(null);
    }

    @Override
    public Set<Item> findAllByBrandDetailsId(Long id) {
        return Sets.newHashSet(itemRepository.findAllByBrandDetails_Id(id));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        return itemRepository.existsByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Item> findAllByManufacturerId(Long id) {
        return Sets.newHashSet(itemRepository.findAllByManufacturer_Id(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<MetadataContainer> findAllIdsAndNames() {
        return itemRepository.findAllBy();
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Item> findAllByBrandDetailsName(String name) {
        return Sets.newHashSet(itemRepository.findAllByBrandDetails_Name(name));
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Item> findAllByManufacturerName(String name) {
        return Sets.newHashSet(itemRepository.findAllByManufacturer_Name(name));
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Item> findAllByCategoriesId(Long id) {
        return Sets.newHashSet(itemRepository.findAllByCategories_Id(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Item> findAllByCategoriesName(String name) {
        return Sets.newHashSet(itemRepository.findAllByCategories_Name(name));
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Item> findAllByDescriptionLike(String desc) {
        return Sets.newHashSet(itemRepository.findAllByDescriptionLike(desc));
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Item> findAllByPriceBetween(int low, int high) {
        return Sets.newHashSet(itemRepository.findAllByPriceBetween(low, high));
    }
}
