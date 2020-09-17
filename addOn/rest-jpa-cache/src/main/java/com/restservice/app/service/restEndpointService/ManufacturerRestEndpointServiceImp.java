package com.restservice.app.service.restEndpointService;

import com.restservice.app.dto.rest.BrandRest;
import com.restservice.app.dto.rest.ItemRest;
import com.restservice.app.dto.rest.ManufacturerRest;
import com.restservice.app.dto.rest.createRestDto.ManufacturerCreateRest;
import com.restservice.app.domain.cache.redis.BrandCache;
import com.restservice.app.domain.cache.redis.ItemCache;
import com.restservice.app.domain.cache.redis.ManufacturerCache;
import com.restservice.app.service.databaseService.BrandDatabaseService;
import com.restservice.app.service.databaseService.ItemDatabaseService;
import com.restservice.app.service.databaseService.ManufacturerDatabaseService;
import com.restservice.app.util.CastRestCreate;
import com.restservice.app.util.ModelAssemblers.RestModelAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Service
public class ManufacturerRestEndpointServiceImp implements ManufacturerRestEndpointService {

    private final Logger logger = LoggerFactory.getLogger(ManufacturerRestEndpointServiceImp.class);

    private final ManufacturerDatabaseService manufacturerDatabaseService;
    private final ItemDatabaseService itemDatabaseService;
    private final BrandDatabaseService brandDatabaseService;
    private final RestModelAssembler restModelAssembler;
    private final CastRestCreate castRestCreate;

    @Autowired
    public ManufacturerRestEndpointServiceImp(ManufacturerDatabaseService manufacturerDatabaseService, ItemDatabaseService itemDatabaseService, BrandDatabaseService brandDatabaseService, RestModelAssembler restModelAssembler, CastRestCreate castRestCreate) {
        this.manufacturerDatabaseService = manufacturerDatabaseService;
        this.itemDatabaseService = itemDatabaseService;
        this.brandDatabaseService = brandDatabaseService;
        this.restModelAssembler = restModelAssembler;
        this.castRestCreate = castRestCreate;
    }

    @Override
    public EntityModel<ManufacturerRest> getManufacturerById(String id) {
        ManufacturerCache manufacturer = manufacturerDatabaseService.getManufacturerById(id);
        return restModelAssembler.manufacturerToManufacturerRestModel(manufacturer);
    }

    @Override
    public EntityModel<ManufacturerRest> getManufacturerByName(String name) {
        ManufacturerCache manufacturer = manufacturerDatabaseService.getManufacturerByName(name);
        return restModelAssembler.manufacturerToManufacturerRestModel(manufacturer);
    }

    @Override
    public EntityModel<ManufacturerRest> createManufacturer(ManufacturerCreateRest manufacturerCreateRest) {
        ManufacturerCache manufacturer = castRestCreate.toManufacturer(manufacturerCreateRest);
        manufacturer = manufacturerDatabaseService.createManufacturer(manufacturer);
        return restModelAssembler.manufacturerToManufacturerRestModel(manufacturer);
    }

    @Override
    public EntityModel<ManufacturerRest> updateManufacturer(ManufacturerCreateRest manufacturerCreateRest) {
        ManufacturerCache manufacturer = castRestCreate.toManufacturer(manufacturerCreateRest);
        manufacturer = manufacturerDatabaseService.updateManufacturer(manufacturer);
        return restModelAssembler.manufacturerToManufacturerRestModel(manufacturer);
    }

    @Override
    public boolean deleteManufacturerById(String id) {
        return manufacturerDatabaseService.deleteManufacturerById(id);
    }

    @Override
    public List<EntityModel<ManufacturerRest>> getAllManufacturers() {
        List<ManufacturerCache> results = manufacturerDatabaseService.getAllManufacturers();
        List<EntityModel<ManufacturerRest>> manufacturersRest = new LinkedList<>();
        results.forEach(m -> manufacturersRest.add(restModelAssembler.manufacturerToManufacturerRestModel(m)));
        return manufacturersRest;
    }

    @Override
    public List<EntityModel<ItemRest>> getAllItemsByManufacturerId(String id) {
        List<ItemCache> results = itemDatabaseService.getAllItemsByManufacturerId(id);
        List<EntityModel<ItemRest>> itemRest = new LinkedList<>();
        results.forEach(m -> itemRest.add(restModelAssembler.itemToItemRestModel(m)));
        return itemRest;
    }

    @Override
    public List<EntityModel<ItemRest>> getAllItemsByManufacturerName(String name) {
        List<ItemCache> results = itemDatabaseService.getAllItemsByManufacturerName(name);
        List<EntityModel<ItemRest>> itemRest = new LinkedList<>();
        results.forEach(m -> itemRest.add(restModelAssembler.itemToItemRestModel(m)));
        return itemRest;
    }

    @Override
    public List<EntityModel<BrandRest>> getAllBrandsByManufacturersId(String id) {
        List<BrandCache> results = brandDatabaseService.getAllBrandsByManufacturersId(id);
        List<EntityModel<BrandRest>> itemRest = new LinkedList<>();
        results.forEach(m -> itemRest.add(restModelAssembler.brandToBrandRestModel(m)));
        return itemRest;
    }

    @Override
    public List<EntityModel<BrandRest>> getAllBrandsByManufacturersName(String name) {
        List<BrandCache> results = brandDatabaseService.getAllBrandsByManufacturersName(name);
        List<EntityModel<BrandRest>> itemRest = new LinkedList<>();
        results.forEach(m -> itemRest.add(restModelAssembler.brandToBrandRestModel(m)));
        return itemRest;
    }

    @Override
    public List<EntityModel<ManufacturerRest>> getAllByAddressCity(String city) {
        List<ManufacturerCache> results = manufacturerDatabaseService.getAllManufacturersByAddressCity(city);
        List<EntityModel<ManufacturerRest>> manRest = new LinkedList<>();
        results.forEach(m -> manRest.add(restModelAssembler.manufacturerToManufacturerRestModel(m)));
        return manRest;
    }

    @Override
    public List<EntityModel<ManufacturerRest>> getAllByAddressCountry(String country) {
        List<ManufacturerCache> results = manufacturerDatabaseService.getAllManufacturersByAddressCountry(country);
        List<EntityModel<ManufacturerRest>> manRest = new LinkedList<>();
        results.forEach(m -> manRest.add(restModelAssembler.manufacturerToManufacturerRestModel(m)));
        return manRest;
    }
}
