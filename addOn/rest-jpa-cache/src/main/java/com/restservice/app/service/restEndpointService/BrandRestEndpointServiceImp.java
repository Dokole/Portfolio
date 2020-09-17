package com.restservice.app.service.restEndpointService;

import com.restservice.app.service.databaseService.BrandDatabaseService;
import com.restservice.app.dto.rest.BrandRest;
import com.restservice.app.dto.rest.ItemRest;
import com.restservice.app.dto.rest.ManufacturerRest;
import com.restservice.app.dto.rest.createRestDto.BrandCreateRest;
import com.restservice.app.domain.cache.redis.BrandCache;
import com.restservice.app.domain.cache.redis.ItemCache;
import com.restservice.app.domain.cache.redis.ManufacturerCache;
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
public class BrandRestEndpointServiceImp implements BrandRestEndpointService {

    private final Logger logger = LoggerFactory.getLogger(BrandRestEndpointServiceImp.class);

    private final BrandDatabaseService brandDatabaseService;
    private final ItemDatabaseService itemDatabaseService;
    private final ManufacturerDatabaseService manufacturerDatabaseService;
    private final RestModelAssembler restModelAssembler;
    private final CastRestCreate castRestCreate;

    @Autowired
    public BrandRestEndpointServiceImp(BrandDatabaseService brandDatabaseService, ItemDatabaseService itemDatabaseService, ManufacturerDatabaseService manufacturerDatabaseService, RestModelAssembler restModelAssembler, CastRestCreate castRestCreate) {
        this.brandDatabaseService = brandDatabaseService;
        this.itemDatabaseService = itemDatabaseService;
        this.manufacturerDatabaseService = manufacturerDatabaseService;
        this.restModelAssembler = restModelAssembler;
        this.castRestCreate = castRestCreate;
    }

    @Override
    public EntityModel<BrandRest> getBrandById(String id) {
        BrandCache brand = brandDatabaseService.getBrandById(id);
        return restModelAssembler.brandToBrandRestModel(brand);
    }

    @Override
    public EntityModel<BrandRest> createBrand(BrandCreateRest brandCreateRest) {
        BrandCache brand = castRestCreate.toBrand(brandCreateRest);
        brand = brandDatabaseService.createBrand(brand);
        return restModelAssembler.brandToBrandRestModel(brand);
    }

    @Override
    public EntityModel<BrandRest> updateBrand(BrandCreateRest brandCreateRest) {
//        System.out.println("IN UPDATE");
//        System.out.println(brandCreateRest.getManufacturersIds());
        BrandCache brand = castRestCreate.toBrand(brandCreateRest);
//        System.out.println("BRAND CACHE IN UPDATE");
//        System.out.println(brand.getManufacturers().get(0).getName());
        brand = brandDatabaseService.updateBrand(brand);
        return restModelAssembler.brandToBrandRestModel(brand);
    }

    @Override
    public boolean deleteBrandById(String id) {
        return brandDatabaseService.deleteBrandById(id);
    }

    @Override
    public List<EntityModel<BrandRest>> getAllBrands() {
        List<BrandCache> results = brandDatabaseService.getAllBrands();
        List<EntityModel<BrandRest>> brandsRest = new LinkedList<>();
        results.forEach(brand -> brandsRest.add(restModelAssembler.brandToBrandRestModel(brand)));
        return brandsRest;
    }

    @Override
    public List<EntityModel<ItemRest>> getAllItemsByBrandId(String id) {
        List<ItemCache> results = itemDatabaseService.getAllItemsByBrandDetailsId(id);
        List<EntityModel<ItemRest>> itemsRest = new LinkedList<>();
        results.forEach(i -> itemsRest.add(restModelAssembler.itemToItemRestModel(i)));
        return itemsRest;
    }

    @Override
    public List<EntityModel<ManufacturerRest>> getAllManufacturersByBrandId(String id) {
        List<ManufacturerCache> manufacturers = manufacturerDatabaseService.getAllManufacturersByBrandsId(id);
        List<EntityModel<ManufacturerRest>> manufacturersRest = new LinkedList();
        manufacturers.forEach(m -> manufacturersRest.add(restModelAssembler.manufacturerToManufacturerRestModel(m)));
        return manufacturersRest;
    }

    @Override
    public EntityModel<BrandRest> getBrandByName(String name) {
        BrandCache brand = brandDatabaseService.getBrandByName(name);
        return restModelAssembler.brandToBrandRestModel(brand);
    }

    @Override
    public List<EntityModel<ItemRest>> getAllItemsByBrandName(String name) {
        List<ItemCache> results = itemDatabaseService.getAllItemsByBrandDetailsName(name);
        List<EntityModel<ItemRest>> itemsRest = new LinkedList<>();
        results.forEach(i -> itemsRest.add(restModelAssembler.itemToItemRestModel(i)));
        return itemsRest;
    }

    @Override
    public List<EntityModel<ManufacturerRest>> getAllManufacturersByBrandsName(String name) {
        List<ManufacturerCache> manufacturers = manufacturerDatabaseService.getAllManufacturersByBrandsName(name);
        List<EntityModel<ManufacturerRest>> manufacturersRest = new LinkedList<>();
        manufacturers.forEach(m -> manufacturersRest.add(restModelAssembler.manufacturerToManufacturerRestModel(m)));
        return manufacturersRest;
    }

    @Override
    public List<EntityModel<BrandRest>> getAllBrandsByAddressCity(String city) {
        List<BrandCache> results = brandDatabaseService.getAllBrandsByAddressCity(city);
        List<EntityModel<BrandRest>> brandsRest = new LinkedList<>();
        results.forEach(brand -> brandsRest.add(restModelAssembler.brandToBrandRestModel(brand)));
        return brandsRest;
    }

    @Override
    public List<EntityModel<BrandRest>> getAllBrandsByAddressCountry(String country) {
        List<BrandCache> results = brandDatabaseService.getAllBrandsByAddressCountry(country);
        List<EntityModel<BrandRest>> brandsRest = new LinkedList<>();
        results.forEach(brand -> brandsRest.add(restModelAssembler.brandToBrandRestModel(brand)));
        return brandsRest;
    }
}
