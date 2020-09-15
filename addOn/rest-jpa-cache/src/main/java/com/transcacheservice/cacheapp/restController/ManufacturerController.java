package com.transcacheservice.cacheapp.restController;

import com.transcacheservice.cacheapp.dto.rest.BrandRest;
import com.transcacheservice.cacheapp.dto.rest.ItemRest;
import com.transcacheservice.cacheapp.dto.rest.ManufacturerRest;
import com.transcacheservice.cacheapp.dto.rest.createRestDto.ManufacturerCreateRest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public interface ManufacturerController {

    EntityModel<ManufacturerRest> getManufacturerById(String id);
    EntityModel<ManufacturerRest> getManufacturerByName(String name);

    EntityModel<ManufacturerRest> createManufacturer(ManufacturerCreateRest manufacturerCreateRest);
    EntityModel<ManufacturerRest> updateManufacturer(ManufacturerCreateRest manufacturerCreateRest, String id);

    boolean deleteManufacturerById(String id);

    CollectionModel<EntityModel<ManufacturerRest>> getAllManufacturers();

    CollectionModel<EntityModel<ItemRest>> getAllItemsByManufacturerId(String id);
    CollectionModel<EntityModel<ItemRest>> getAllItemsByManufacturerName(String name);

    CollectionModel<EntityModel<BrandRest>> getAllBrandsByManufacturerId(String id);
    CollectionModel<EntityModel<BrandRest>> getAllBrandsByManufacturerName(String name);

    CollectionModel<EntityModel<ManufacturerRest>> getAllManufacturersByCity(String city);
    CollectionModel<EntityModel<ManufacturerRest>> getAllManufacturersByCountry(String country);
}
