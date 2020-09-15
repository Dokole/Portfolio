package com.producingservice.app.util;

import com.producingservice.app.domain.*;
import database.dto.app.producingservice.com.*;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Component("castToDtoUtil")
public class CastToDtoUtil {

    //ITEMS
    public ItemDto itemToItemDto(Item item) {
        ItemDto itemDto = itemToItemDtoBasic(item);
        if (Hibernate.isInitialized(item.getBrandDetails()) && item.getBrandDetails() != null) {
            itemDto.setBrandDetails(brandToBrandDto(item.getBrandDetails()));
        }
        if(Hibernate.isInitialized(item.getManufacturer()) && item.getManufacturer() != null) {
            itemDto.setManufacturer(manufacturerToManufacturerDto(item.getManufacturer()));
        }
        if(Hibernate.isInitialized(item.getCategories()) && item.getCategories() != null) {
            for (Category cat : item.getCategories()) {
                itemDto.getCategories().add(categoryToCategoryDto(cat));
            }
        }
        return itemDto;
    }

    public Item itemDtoToItem(ItemDto itemDto) {
        Item item = itemDtoToItemBasic(itemDto);

        if (itemDto.getBrandDetails() != null) {
            item.setBrandDetails(brandDtoToBrand(itemDto.getBrandDetails()));
        }
        if(itemDto.getManufacturer() != null) {
            item.setManufacturer(manufacturerDtoToManufacturer(itemDto.getManufacturer()));
        }
        if(itemDto.getCategories() != null) {
            for (CategoryDto cat : itemDto.getCategories()) {
                item.getCategories().add(categoryDtoToCategory(cat));
            }
        }
        return item;
    }

    //BRANDS
    public BrandDto brandToBrandDto(Brand brand) {
        BrandDto brandDto = brandToBrandDtoBasic(brand);
        if (Hibernate.isInitialized(brand.getManufacturers()) && brand.getManufacturers() != null) {
            for (Manufacturer man : brand.getManufacturers()) {
                brandDto.getManufacturersList().add(manufacturerToManufacturerDto(man));
            }
        }
        return brandDto;
    }

    public Brand brandDtoToBrand(BrandDto brandDto) {
        Brand brand = brandDtoToBrandBasic(brandDto);
        if (brandDto.getManufacturersList() != null) {
            for (ManufacturerDto man : brandDto.getManufacturersList()) {
                brand.getManufacturers().add(manufacturerDtoToManufacturer(man));
            }
        }
        return brand;
    }

    //MANUFACTURERS
    public ManufacturerDto manufacturerToManufacturerDto(Manufacturer manufacturer) {
        ManufacturerDto manufacturerDto = manufacturerToManufacturerDtoBasic(manufacturer);
        if (Hibernate.isInitialized(manufacturer.getBrands()) && manufacturer.getBrands() != null) {
            for (Brand brand : manufacturer.getBrands()) {
                manufacturerDto.getBrandsList().add(brandToBrandDto(brand));
            }
        }
        return manufacturerDto;
    }

    public Manufacturer manufacturerDtoToManufacturer(ManufacturerDto manufacturerDto) {
        Manufacturer manufacturer = manufacturerDtoToManufacturerBasic(manufacturerDto);
        if (manufacturerDto.getBrandsList() != null) {
            for (BrandDto brand : manufacturerDto.getBrandsList()) {
                manufacturer.getBrands().add(brandDtoToBrand(brand));
            }
        }
        return manufacturer;
    }
    //CATEGORY
    public CategoryDto categoryToCategoryDto(Category category) {
        CategoryDto categoryDto = categoryToCategoryDtoBasic(category);
        if (Hibernate.isInitialized(category.getItems()) && category.getItems() != null) {
            for (Item item : category.getItems()) {
                categoryDto.getItemsList().add(itemToItemDto(item));
            }
        }
        return categoryDto;
    }
    public Category categoryDtoToCategory(CategoryDto categoryDto) {
        Category category = categoryDtoToCategoryBasic(categoryDto);
        if (categoryDto.getItemsList() != null) {
            for (ItemDto item : categoryDto.getItemsList()) {
                category.getItems().add(itemDtoToItem(item));
            }
        }
        return category;
    }

    public AddressDto addressToAddressDto(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setCountry(address.getCountry());
        addressDto.setCity(address.getCity());
        addressDto.setStreet(address.getStreet());
        addressDto.setHouse(address.getHouse());
        return addressDto;
    }

    public Address addressDtoToAddress(AddressDto addressDto) {
        Address address = new Address();
        address.setCountry(addressDto.getCountry());
        address.setCity(addressDto.getCity());
        address.setStreet(addressDto.getStreet());
        address.setHouse(addressDto.getHouse());
        return address;
    }

    public MetaContainerDto metaContainerToDto(MetadataContainer metaContainer) {
        MetaContainerDto metaContainerDto = new MetaContainerDto();
        metaContainerDto.setId(metaContainer.getId());
        metaContainerDto.setName(metaContainer.getName());
        return metaContainerDto;
    }



    /**
     * Methods for repetitive code, basic and must-have configuration of objects
     */

    private ItemDto itemToItemDtoBasic(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setVersion(item.getVersion());
        itemDto.setLastModifiedBy(item.getLastModifiedBy().orElse(null));
        itemDto.setName(item.getName());
        itemDto.setDescription(item.getDescription());
        itemDto.setPrice(item.getPrice());
        return itemDto;
    }
    private Item itemDtoToItemBasic(ItemDto itemDto) {
        Item item = new Item();
        item.setId(itemDto.getId());
        item.setVersion(itemDto.getVersion());
        item.setLastModifiedBy(itemDto.getLastModifiedBy());
        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());
        item.setPrice(itemDto.getPrice());
        return item;
    }

    private BrandDto brandToBrandDtoBasic(Brand brand) {
        BrandDto brandDto = new BrandDto();
        brandDto.setId(brand.getId());
        brandDto.setVersion(brand.getVersion());
        brandDto.setLastModifiedBy(brand.getLastModifiedBy().orElse(null));
        brandDto.setName(brand.getName());
        brandDto.setOfficeAddress(addressToAddressDto(brand.getOfficeAddress()));
        return brandDto;
    }

    private Brand brandDtoToBrandBasic(BrandDto brandDto) {
        Brand brand = new Brand();
        brand.setId(brandDto.getId());
        brand.setVersion(brandDto.getVersion());
        brand.setLastModifiedBy(brandDto.getLastModifiedBy());
        brand.setName(brandDto.getName());
        brand.setOfficeAddress(addressDtoToAddress(brandDto.getOfficeAddress()));
        return brand;
    }

    private ManufacturerDto manufacturerToManufacturerDtoBasic(Manufacturer manufacturer) {
        ManufacturerDto manufacturerDto = new ManufacturerDto();
        manufacturerDto.setId(manufacturer.getId());
        manufacturerDto.setVersion(manufacturer.getVersion());
        manufacturerDto.setLastModifiedBy(manufacturer.getLastModifiedBy().orElse(null));
        manufacturerDto.setName(manufacturer.getName());
        manufacturerDto.setAddress(addressToAddressDto(manufacturer.getAddress()));
        return manufacturerDto;
    }

    private Manufacturer manufacturerDtoToManufacturerBasic(ManufacturerDto manufacturerDto) {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(manufacturerDto.getId());
        manufacturer.setVersion(manufacturerDto.getVersion());
        manufacturer.setLastModifiedBy(manufacturerDto.getLastModifiedBy());
        manufacturer.setName(manufacturerDto.getName());
        manufacturer.setAddress(addressDtoToAddress(manufacturerDto.getAddress()));
        return manufacturer;
    }

    private CategoryDto categoryToCategoryDtoBasic(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setVersion(category.getVersion());
        categoryDto.setLastModifiedBy(category.getLastModifiedBy().orElse(null));
        categoryDto.setName(category.getName());
        return categoryDto;
    }

    private Category categoryDtoToCategoryBasic(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setVersion(categoryDto.getVersion());
        category.setLastModifiedBy(categoryDto.getLastModifiedBy());
        category.setName(categoryDto.getName());
        return category;
    }



}
