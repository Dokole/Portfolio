package com.transcacheservice.cacheapp.restController;

import com.transcacheservice.cacheapp.dto.rest.BrandRest;
import com.transcacheservice.cacheapp.dto.rest.ItemRest;
import com.transcacheservice.cacheapp.dto.rest.ManufacturerRest;
import com.transcacheservice.cacheapp.dto.rest.createRestDto.BrandCreateRest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import java.util.List;

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
