package com.restservice.app.restController;

import com.restservice.app.dto.rest.BrandRest;
import com.restservice.app.dto.rest.ItemRest;
import com.restservice.app.dto.rest.ManufacturerRest;
import com.restservice.app.dto.rest.createRestDto.BrandCreateRest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public interface BrandController {

    EntityModel<BrandRest> getBrandById(String id);
    EntityModel<BrandRest> getBrandByName(String name);

    EntityModel<BrandRest> createBrand(BrandCreateRest brandCreateRest);
    EntityModel<BrandRest> updateBrand(BrandCreateRest brandCreateRest, String id);

    boolean deleteBrandById(String id);

    CollectionModel<EntityModel<BrandRest>> getAllBrands();

    CollectionModel<EntityModel<ItemRest>> getAllItemsByBrandId(String id);
    CollectionModel<EntityModel<ItemRest>> getAllItemsByBrandName(String name);

    CollectionModel<EntityModel<ManufacturerRest>> getAllManufacturersByBrandId(String id);
    CollectionModel<EntityModel<ManufacturerRest>> getAllManufacturersByBrandName(String name);

    CollectionModel<EntityModel<BrandRest>> getAllBrandsByAddressCity(String city);
    CollectionModel<EntityModel<BrandRest>> getAllBrandsByAddressCountry(String country);
}
