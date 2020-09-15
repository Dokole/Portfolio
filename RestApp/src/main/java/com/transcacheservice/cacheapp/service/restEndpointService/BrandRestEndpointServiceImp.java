package com.transcacheservice.cacheapp.service.restEndpointService;

import com.transcacheservice.cacheapp.dto.rest.BrandRest;
import com.transcacheservice.cacheapp.dto.rest.ItemRest;
import com.transcacheservice.cacheapp.dto.rest.ManufacturerRest;
import com.transcacheservice.cacheapp.dto.rest.createRestDto.BrandCreateRest;
import com.transcacheservice.cacheapp.domain.cache.redis.BrandCache;
import com.transcacheservice.cacheapp.domain.cache.redis.ItemCache;
import com.transcacheservice.cacheapp.domain.cache.redis.ManufacturerCache;
import com.transcacheservice.cacheapp.exceptions.BadRequestException;
import com.transcacheservice.cacheapp.service.soapService.BrandSoapService;
import com.transcacheservice.cacheapp.service.soapService.ItemSoapService;
import com.transcacheservice.cacheapp.service.soapService.ManufacturerSoapService;
import com.transcacheservice.cacheapp.util.CastRestCreate;
import com.transcacheservice.cacheapp.util.ModelAssemblers.RestModelAssembler;
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

    private final BrandSoapService brandSoapService;
    private final ItemSoapService itemSoapService;
    private final ManufacturerSoapService manufacturerSoapService;
    private final RestModelAssembler restModelAssembler;
    private final CastRestCreate castRestCreate;

    @Autowired
    public BrandRestEndpointServiceImp(BrandSoapService brandSoapService, ItemSoapService itemSoapService, ManufacturerSoapService manufacturerSoapService, RestModelAssembler restModelAssembler, CastRestCreate castRestCreate) {
        this.brandSoapService = brandSoapService;
        this.itemSoapService = itemSoapService;
        this.manufacturerSoapService = manufacturerSoapService;
        this.restModelAssembler = restModelAssembler;
        this.castRestCreate = castRestCreate;
    }

    @Override
    public EntityModel<BrandRest> getBrandById(String id) {
        try {
            BrandCache brand = brandSoapService.getBrandById(Long.parseLong(id));
            return restModelAssembler.brandToBrandRestModel(brand);
        } catch (NumberFormatException e) {
        logger.error("Illegal URI argument = " + id + " Message: " + e.getLocalizedMessage());
        throw new BadRequestException("Illegal URI argument = " + id);
        }
    }

    @Override
    public EntityModel<BrandRest> createBrand(BrandCreateRest brandCreateRest) {
        BrandCache brand = castRestCreate.toBrand(brandCreateRest);
        brand = brandSoapService.createBrand(brand);
        return restModelAssembler.brandToBrandRestModel(brand);
    }

    @Override
    public EntityModel<BrandRest> updateBrand(BrandCreateRest brandCreateRest) {
        BrandCache brand = castRestCreate.toBrand(brandCreateRest);
        brand = brandSoapService.updateBrand(brand);
        return restModelAssembler.brandToBrandRestModel(brand);
    }

    @Override
    public boolean deleteBrandById(String id) {
        try {
            return brandSoapService.deleteBrandById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            logger.error("Illegal URI argument = " + id + " Message: " + e.getLocalizedMessage());
            throw new BadRequestException("Illegal URI argument = " + id);
        }
    }

    @Override
    public List<EntityModel<BrandRest>> getAllBrands() {
        List<BrandCache> results = brandSoapService.getAllBrands();
        List<EntityModel<BrandRest>> brandsRest = new LinkedList<>();
        results.forEach(brand -> brandsRest.add(restModelAssembler.brandToBrandRestModel(brand)));
        return brandsRest;
    }

    @Override
    public List<EntityModel<ItemRest>> getAllItemsByBrandId(String id) {
        try {
            List<ItemCache> results = itemSoapService.getItemsByBrandId(Long.parseLong(id));
            List<EntityModel<ItemRest>> itemsRest = new LinkedList<>();
            results.forEach(i -> itemsRest.add(restModelAssembler.itemToItemRestModel(i)));
            return itemsRest;
        } catch (NumberFormatException e) {
            logger.error("Illegal URI argument = " + id + " Message: " + e.getLocalizedMessage());
            throw new BadRequestException("Illegal URI argument = " + id);
        }


    }

    @Override
    public List<EntityModel<ManufacturerRest>> getAllManufacturersByBrandId(String id) {
        try {
            List<ManufacturerCache> manufacturers = manufacturerSoapService.getManufacturersByBrandsId(Long.parseLong(id));
            List<EntityModel<ManufacturerRest>> manufacturersRest = new LinkedList();
            manufacturers.forEach(m -> manufacturersRest.add(restModelAssembler.manufacturerToManufacturerRestModel(m)));
            return manufacturersRest;
        } catch (NumberFormatException e) {
            logger.error("Illegal URI argument = " + id + " Message: " + e.getLocalizedMessage());
            throw new BadRequestException("Illegal URI argument = " + id);
        }

    }

    @Override
    public EntityModel<BrandRest> getBrandByName(String name) {
        BrandCache brand = brandSoapService.getBrandByName(name);
        return restModelAssembler.brandToBrandRestModel(brand);
    }

    @Override
    public List<EntityModel<ItemRest>> getAllItemsByBrandName(String name) {
        List<ItemCache> results = itemSoapService.getItemsByBrandName(name);
        List<EntityModel<ItemRest>> itemsRest = new LinkedList<>();
        results.forEach(i -> itemsRest.add(restModelAssembler.itemToItemRestModel(i)));
        return itemsRest;
    }

    @Override
    public List<EntityModel<ManufacturerRest>> getAllManufacturersByBrandsName(String name) {
        List<ManufacturerCache> manufacturers = manufacturerSoapService.getManufacturersByBrandsName(name);
        List<EntityModel<ManufacturerRest>> manufacturersRest = new LinkedList<>();
        manufacturers.forEach(m -> manufacturersRest.add(restModelAssembler.manufacturerToManufacturerRestModel(m)));
        return manufacturersRest;
    }

    @Override
    public List<EntityModel<BrandRest>> getAllBrandsByAddressCity(String city) {
        List<BrandCache> results = brandSoapService.getBrandsByAddressCity(city);
        List<EntityModel<BrandRest>> brandsRest = new LinkedList<>();
        results.forEach(brand -> brandsRest.add(restModelAssembler.brandToBrandRestModel(brand)));
        return brandsRest;
    }

    @Override
    public List<EntityModel<BrandRest>> getAllBrandsByAddressCountry(String country) {
        List<BrandCache> results = brandSoapService.getBrandsByAddressCountry(country);
        List<EntityModel<BrandRest>> brandsRest = new LinkedList<>();
        results.forEach(brand -> brandsRest.add(restModelAssembler.brandToBrandRestModel(brand)));
        return brandsRest;
    }
}
