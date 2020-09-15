package com.transcacheservice.cacheapp.util;

import com.transcacheservice.cacheapp.dto.rest.*;
import com.transcacheservice.cacheapp.dto.rest.createRestDto.BrandCreateRest;
import com.transcacheservice.cacheapp.dto.rest.createRestDto.CategoryCreateRest;
import com.transcacheservice.cacheapp.dto.rest.createRestDto.ItemCreateRest;
import com.transcacheservice.cacheapp.dto.rest.createRestDto.ManufacturerCreateRest;
import com.transcacheservice.cacheapp.domain.cache.redis.*;
import com.transcacheservice.cacheapp.service.AuditorAwareBean;
import com.transcacheservice.cacheapp.service.databaseService.BrandDatabaseService;
import com.transcacheservice.cacheapp.service.databaseService.CategoryDatabaseService;
import com.transcacheservice.cacheapp.service.databaseService.ItemDatabaseService;
import com.transcacheservice.cacheapp.service.databaseService.ManufacturerDatabaseService;
import com.transcacheservice.cacheapp.util.ModelAssemblers.RestModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Component
public class CastRestCreate {

    private final RestModelAssembler restModelAssembler;
    private final BrandDatabaseService brandDatabaseService;
    private final ManufacturerDatabaseService manufacturerDatabaseService;
    private final ItemDatabaseService itemDatabaseService;
    private final CategoryDatabaseService categoryDatabaseService;
    private final AuditorAwareBean auditorAwareBean;

    @Autowired
    public CastRestCreate(RestModelAssembler restModelAssembler, BrandDatabaseService brandDatabaseService, ManufacturerDatabaseService manufacturerDatabaseService, ItemDatabaseService itemDatabaseService, CategoryDatabaseService categoryDatabaseService, AuditorAwareBean auditorAwareBean) {
        this.restModelAssembler = restModelAssembler;
        this.brandDatabaseService = brandDatabaseService;
        this.manufacturerDatabaseService = manufacturerDatabaseService;
        this.itemDatabaseService = itemDatabaseService;
        this.categoryDatabaseService = categoryDatabaseService;
        this.auditorAwareBean = auditorAwareBean;
    }

    public BrandCache toBrand(BrandCreateRest brandCreateRest) {
        BrandCache brand = new BrandCache();
        if (brandCreateRest.getId() == null) {
            brand.setId(null);
        } else brand.setId(Long.parseLong(brandCreateRest.getId()));

        brand.setVersion(brandCreateRest.getVersion());
        brand.setLastModifiedBy(auditorAwareBean.getCurrentAuditor().orElse("Unknown"));
        brand.setName(brandCreateRest.getName());
        brand.setAddress(restModelAssembler.addressRestToAddress(brandCreateRest.getAddress()));
        if (brandCreateRest.getManufacturersIds() != null) {
            brandCreateRest.getManufacturersIds().forEach(m -> brand.getManufacturers().add(manufacturerDatabaseService.getManufacturerById(String.valueOf(m))));
        }
        return brand;
    }

    public CategoryCache toCategory(CategoryCreateRest categoryRest) {
        CategoryCache category = new CategoryCache();
        if (categoryRest.getId() == null) {
            category.setId(null);
        } else category.setId(Long.parseLong(categoryRest.getId()));

        category.setVersion(categoryRest.getVersion());
        category.setLastModifiedBy(auditorAwareBean.getCurrentAuditor().orElse("Unknown"));
        category.setName(categoryRest.getName());

        if(categoryRest.getItemsIds() != null) {
            categoryRest.getItemsIds().forEach(i -> category.getItems().add(itemDatabaseService.getItemById(String.valueOf(i))));
        }
        return category;
    }

    public ManufacturerCache toManufacturer(ManufacturerCreateRest manufacturerRest) {
        ManufacturerCache manufacturer = new ManufacturerCache();
        if (manufacturerRest.getId() == null) {
            manufacturer.setId(null);
        } else manufacturer.setId(Long.parseLong(manufacturerRest.getId()));
        manufacturer.setVersion(manufacturerRest.getVersion());
        manufacturer.setLastModifiedBy(auditorAwareBean.getCurrentAuditor().orElse("Unknown"));
        manufacturer.setName(manufacturerRest.getName());
        manufacturer.setAddress(addressFromAddressRest(manufacturerRest.getAddress()));

        if (manufacturerRest.getBrandsIds() != null) {
            manufacturerRest.getBrandsIds().forEach(b -> manufacturer.getBrands().add(brandDatabaseService.getBrandById(String.valueOf(b))));
        }
        return manufacturer;
    }

    public ItemCache toItem(ItemCreateRest itemRest) {
        ItemCache item = new ItemCache();
        if (itemRest.getId() == null) {
            item.setId(null);
        } else item.setId(Long.parseLong(itemRest.getId()));
        item.setVersion(itemRest.getVersion());
        item.setLastModifiedBy(auditorAwareBean.getCurrentAuditor().orElse("Unknown"));
        item.setName(itemRest.getName());
        item.setDescription(itemRest.getDescription());
        item.setPrice(itemRest.getPrice());

        if (itemRest.getBrandDetailsId() != null) {
            item.setBrand(brandDatabaseService.getBrandById(String.valueOf(itemRest.getBrandDetailsId())));
        }
        if (itemRest.getManufacturerId() != null) {
            item.setManufacturer(manufacturerDatabaseService.getManufacturerById(String.valueOf(itemRest.getManufacturerId())));
        }
        if (itemRest.getCategories() != null) {
            itemRest.getCategories().forEach(c -> item.getCategories().add(categoryDatabaseService.getCategoryById(String.valueOf(c))));
        }
        return item;
    }


    public AddressCache addressFromAddressRest(AddressRest addressRest) {
        AddressCache address = new AddressCache();
        address.setCountry(addressRest.getCountry());
        address.setCity(addressRest.getCity());
        address.setStreet(addressRest.getStreet());
        address.setHouse(addressRest.getHouse());
        return address;
    }
}
