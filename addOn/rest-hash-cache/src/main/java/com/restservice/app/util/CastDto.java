package com.restservice.app.util;

import com.restservice.app.domain.cache.redis.*;
import com.restservice.app.dto.database.*;
import com.restservice.app.dto.CreationData;
import com.restservice.app.dto.MetaContainer;
import org.springframework.stereotype.Component;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Component
public class CastDto {

    public BrandCache brandFromBrandDto(BrandDto brandDto) {
        BrandCache brand = new BrandCache();
        brand.setId(brandDto.getId());
        brand.setVersion(brandDto.getVersion());
        brand.setLastModifiedBy(brandDto.getLastModifiedBy());
        brand.setName(brandDto.getName());
        brand.setAddress(addressFromAddressDto(brandDto.getOfficeAddress()));

        return brand;
    }

    public BrandDto brandDtoFromBrand(BrandCache brand) {
        BrandDto brandDto = new BrandDto();
        brandDto.setId(brand.getId());
        brandDto.setVersion(brand.getVersion());
        brandDto.setLastModifiedBy(brand.getLastModifiedBy());
        brandDto.setName(brand.getName());
        brandDto.setOfficeAddress(addressDtoToAddress(brand.getAddress()));

        if (brand.getManufacturers() != null) {
            brand.getManufacturers().forEach(m -> brandDto.getManufacturersList().add(manufacturerDtoFromManufacturer(m)));
        }
        return brandDto;
    }

    public CategoryCache categoryFromCategoryDto(CategoryDto categoryDto) {
        CategoryCache category = new CategoryCache();
        category.setId(categoryDto.getId());
        category.setVersion(categoryDto.getVersion());
        category.setLastModifiedBy(categoryDto.getLastModifiedBy());
        category.setName(categoryDto.getName());

        if(categoryDto.getItemsList() != null) {
            categoryDto.getItemsList().forEach(itemDto -> category.getItems().add(itemFromItemDto(itemDto)));
        }
        return category;
    }

    public CategoryDto categoryDtoFromCategory(CategoryCache category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setVersion(category.getVersion());
        categoryDto.setLastModifiedBy(category.getLastModifiedBy());
        categoryDto.setName(category.getName());

        if(category.getItems() != null) {
            category.getItems().forEach(item -> categoryDto.getItemsList().add(itemDtoFromItem(item)));
        }
        return categoryDto;
    }

    public ManufacturerCache manufacturerFromManufacturerDto(ManufacturerDto manufacturerDto) {
        ManufacturerCache manufacturer = new ManufacturerCache();
        manufacturer.setId(manufacturerDto.getId());
        manufacturer.setVersion(manufacturerDto.getVersion());
        manufacturer.setLastModifiedBy(manufacturerDto.getLastModifiedBy());
        manufacturer.setName(manufacturerDto.getName());
        manufacturer.setAddress(addressFromAddressDto(manufacturerDto.getAddress()));

        if (manufacturerDto.getBrandsList() != null) {
            manufacturerDto.getBrandsList().forEach(b -> manufacturer.getBrands().add(brandFromBrandDto(b)));
        }
        return manufacturer;
    }

    public ManufacturerDto manufacturerDtoFromManufacturer(ManufacturerCache manufacturer) {
        ManufacturerDto manufacturerDto = new ManufacturerDto();
        manufacturerDto.setId(manufacturer.getId());
        manufacturerDto.setVersion(manufacturer.getVersion());
        manufacturerDto.setLastModifiedBy(manufacturer.getLastModifiedBy());
        manufacturerDto.setName(manufacturer.getName());
        manufacturerDto.setAddress(addressDtoToAddress(manufacturer.getAddress()));

        if (manufacturer.getBrands() != null) {
            manufacturer.getBrands().forEach(b -> manufacturerDto.getBrandsList().add(brandDtoFromBrand(b)));
        }
        return manufacturerDto;
    }

    public ItemCache itemFromItemDto(ItemDto itemDto) {
        ItemCache item = new ItemCache();
        item.setId(itemDto.getId());
        item.setVersion(itemDto.getVersion());
        item.setLastModifiedBy(itemDto.getLastModifiedBy());
        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());
        item.setPrice(itemDto.getPrice());

        if (itemDto.getBrandDetails() != null) {
            item.setBrand(brandFromBrandDto(itemDto.getBrandDetails()));
        }
        if (itemDto.getManufacturer() != null) {
            item.setManufacturer(manufacturerFromManufacturerDto(itemDto.getManufacturer()));
        }
        if (itemDto.getCategories() != null) {
            itemDto.getCategories().forEach(c -> item.getCategories().add(categoryFromCategoryDto(c)));
        }
        return item;
    }

    public ItemDto itemDtoFromItem(ItemCache item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setVersion(item.getVersion());
        itemDto.setLastModifiedBy(item.getLastModifiedBy());
        itemDto.setName(item.getName());
        itemDto.setDescription(item.getDescription());
        itemDto.setPrice(item.getPrice());

        if (item.getBrand() != null) {
            itemDto.setBrandDetails(brandDtoFromBrand(item.getBrand()));
        }
        if (item.getManufacturer() != null) {
            itemDto.setManufacturer(manufacturerDtoFromManufacturer(item.getManufacturer()));
        }
        if (item.getCategories() != null) {
            item.getCategories().forEach(c -> itemDto.getCategories().add(categoryDtoFromCategory(c)));
        }
        return itemDto;
    }


    public AddressCache addressFromAddressDto(AddressDto addressDto) {
        AddressCache address = new AddressCache();
        address.setCountry(addressDto.getCountry());
        address.setCity(addressDto.getCity());
        address.setStreet(addressDto.getStreet());
        address.setHouse(addressDto.getHouse());
        return address;
    }

    public AddressDto addressDtoToAddress(AddressCache address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setCountry(address.getCountry());
        addressDto.setCity(address.getCity());
        addressDto.setStreet(address.getStreet());
        addressDto.setHouse(address.getHouse());
        return addressDto;
    }

    public CreationData creationDataDtoToCreationData(CreateDataDto createDataDto) {
        CreationData creationData = new CreationData();
        createDataDto.getBrandsMeta().forEach(b -> creationData.getBrands().add(
                metaContainerDtoToMetaContainer(b)
        ));
        createDataDto.getCategoriesMeta().forEach(c -> creationData.getCategories().add(
                metaContainerDtoToMetaContainer(c)
        ));
        createDataDto.getItemsMeta().forEach(i -> creationData.getItems().add(
                metaContainerDtoToMetaContainer(i)
        ));
        createDataDto.getManufacturersMeta().forEach(m -> creationData.getManufacturers().add(
                metaContainerDtoToMetaContainer(m)
        ));
        return creationData;
    }
    public MetaContainer metaContainerDtoToMetaContainer(MetaContainerDto metaContainerDto) {
        MetaContainer metaContainer = new MetaContainer();
        metaContainer.setId(metaContainerDto.getId());
        metaContainer.setName(metaContainerDto.getName());
        return metaContainer;
    }
}
