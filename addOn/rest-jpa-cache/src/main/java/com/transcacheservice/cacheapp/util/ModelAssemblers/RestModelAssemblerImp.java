package com.transcacheservice.cacheapp.util.ModelAssemblers;


import com.transcacheservice.cacheapp.dto.CreationData;
import com.transcacheservice.cacheapp.dto.rest.*;
import com.transcacheservice.cacheapp.dto.MetaContainer;
import com.transcacheservice.cacheapp.domain.cache.redis.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Component
public class RestModelAssemblerImp implements RestModelAssembler{
    private final BrandModelAssembler brandModelAssembler;
    private final ItemModelAssembler itemModelAssembler;
    private final CategoryModelAssembler categoryModelAssembler;
    private final ManufacturerModelAssembler manufacturerModelAssembler;

    @Autowired
    public RestModelAssemblerImp(BrandModelAssembler brandModelAssembler, ItemModelAssembler itemModelAssembler, CategoryModelAssembler categoryModelAssembler, ManufacturerModelAssembler manufacturerModelAssembler) {
        this.brandModelAssembler = brandModelAssembler;
        this.itemModelAssembler = itemModelAssembler;
        this.categoryModelAssembler = categoryModelAssembler;
        this.manufacturerModelAssembler = manufacturerModelAssembler;
    }

    @Override
    public EntityModel<BrandRest> brandToBrandRestModel(BrandCache brand) {
        BrandRest brandRest = new BrandRest();
        brandRest.setId(String.valueOf(brand.getId()));
        brandRest.setVersion(brand.getVersion());
        brandRest.setName(brand.getName());
        brandRest.setAddress(addressToAddressRest(brand.getAddress()));

        return brandModelAssembler.toModel(brandRest);
    }

    @Override
    public EntityModel<CategoryRest> categoryToCategoryRestModel(CategoryCache category) {
        CategoryRest categoryRest = new CategoryRest();
        categoryRest.setId(String.valueOf(category.getId()));
        categoryRest.setVersion(category.getVersion());
        categoryRest.setName(category.getName());

        return categoryModelAssembler.toModel(categoryRest);
    }

    @Override
    public EntityModel<ManufacturerRest> manufacturerToManufacturerRestModel(ManufacturerCache manufacturer) {
        ManufacturerRest manufacturerRest = new ManufacturerRest();
        manufacturerRest.setId(String.valueOf(manufacturer.getId()));
        manufacturerRest.setVersion(manufacturer.getVersion());
        manufacturerRest.setName(manufacturer.getName());
        manufacturerRest.setAddress(addressToAddressRest(manufacturer.getAddress()));

        return manufacturerModelAssembler.toModel(manufacturerRest);
    }

    @Override
    public EntityModel<ItemRest> itemToItemRestModel(ItemCache item) {
        ItemRest itemRest = new ItemRest();
        itemRest.setId(String.valueOf(item.getId()));
        itemRest.setVersion(item.getVersion());
        itemRest.setName(item.getName());
        itemRest.setDescription(item.getDescription());
        itemRest.setPrice(item.getPrice());

        return itemModelAssembler.toModel(itemRest);
    }

    @Override
    public AddressCache addressRestToAddress(AddressRest addressRest) {
        AddressCache address = new AddressCache();
        address.setCountry(addressRest.getCountry());
        address.setCity(addressRest.getCity());
        address.setStreet(addressRest.getStreet());
        address.setHouse(addressRest.getHouse());
        return address;
    }

    @Override
    public AddressRest addressToAddressRest(AddressCache address) {
        AddressRest addressRest = new AddressRest();
        addressRest.setCountry(address.getCountry());
        addressRest.setCity(address.getCity());
        addressRest.setStreet(address.getStreet());
        addressRest.setHouse(address.getHouse());
        return addressRest;
    }

    @Override
    public CreationDataRest creationDataToCreationDataRest(CreationData creationData) {
        CreationDataRest creationDataRest = new CreationDataRest();
        creationData.getBrands().forEach(b -> creationDataRest.getBrands().add(
                metaContainerToMetaContainerRest(b)
        ));
        creationData.getManufacturers().forEach(m -> creationDataRest.getManufacturers().add(
                metaContainerToMetaContainerRest(m)
        ));
        creationData.getCategories().forEach(c -> creationDataRest.getCategories().add(
                metaContainerToMetaContainerRest(c)
        ));
        creationData.getItems().forEach(i -> creationDataRest.getItems().add(
                metaContainerToMetaContainerRest(i)
        ));
        return creationDataRest;
    }

    @Override
    public MetaContainerRest metaContainerToMetaContainerRest(MetaContainer metaContainer) {
        MetaContainerRest metaContainerRest = new MetaContainerRest();
        metaContainerRest.setId(metaContainer.getId());
        metaContainerRest.setName(metaContainer.getName());
        return metaContainerRest;
    }

}
