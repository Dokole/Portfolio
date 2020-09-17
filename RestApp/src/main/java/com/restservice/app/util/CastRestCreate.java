package com.restservice.app.util;

import com.restservice.app.domain.cache.redis.*;
import com.restservice.app.dto.rest.AddressRest;
import com.restservice.app.dto.rest.createRestDto.BrandCreateRest;
import com.restservice.app.dto.rest.createRestDto.CategoryCreateRest;
import com.restservice.app.dto.rest.createRestDto.ItemCreateRest;
import com.restservice.app.dto.rest.createRestDto.ManufacturerCreateRest;
import com.restservice.app.service.AuditorAwareBean;
import com.restservice.app.service.soapService.BrandSoapService;
import com.restservice.app.service.soapService.CategorySoapService;
import com.restservice.app.service.soapService.ItemSoapService;
import com.restservice.app.service.soapService.ManufacturerSoapService;
import com.restservice.app.util.ModelAssemblers.RestModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Component
public class CastRestCreate {

    private final RestModelAssembler restModelAssembler;
    private final BrandSoapService brandSoapService;
    private final ManufacturerSoapService manufacturerSoapService;
    private final ItemSoapService itemSoapService;
    private final CategorySoapService categorySoapService;
    private final AuditorAwareBean auditorAwareBean;

    @Autowired
    public CastRestCreate(RestModelAssembler restModelAssembler, BrandSoapService brandSoapService, ManufacturerSoapService manufacturerSoapService, ItemSoapService itemSoapService, CategorySoapService categorySoapService, AuditorAwareBean auditorAwareBean) {
        this.restModelAssembler = restModelAssembler;
        this.brandSoapService = brandSoapService;
        this.manufacturerSoapService = manufacturerSoapService;
        this.itemSoapService = itemSoapService;
        this.categorySoapService = categorySoapService;
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
            brandCreateRest.getManufacturersIds().forEach(m -> brand.getManufacturers().add(manufacturerSoapService.getManufacturerById(m)));
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
            categoryRest.getItemsIds().forEach(i -> category.getItems().add(itemSoapService.getItemById(i)));
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
            manufacturerRest.getBrandsIds().forEach(b -> manufacturer.getBrands().add(brandSoapService.getBrandById(b)));
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
            item.setBrand(brandSoapService.getBrandById(itemRest.getBrandDetailsId()));
        }
        if (itemRest.getManufacturerId() != null) {
            item.setManufacturer(manufacturerSoapService.getManufacturerById(itemRest.getManufacturerId()));
        }
        if (itemRest.getCategories() != null) {
            itemRest.getCategories().forEach(c -> item.getCategories().add(categorySoapService.getCategoryById(c)));
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
