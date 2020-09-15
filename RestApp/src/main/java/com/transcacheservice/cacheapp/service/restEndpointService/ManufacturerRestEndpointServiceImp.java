package com.transcacheservice.cacheapp.service.restEndpointService;

import com.transcacheservice.cacheapp.dto.rest.BrandRest;
import com.transcacheservice.cacheapp.dto.rest.ItemRest;
import com.transcacheservice.cacheapp.dto.rest.ManufacturerRest;
import com.transcacheservice.cacheapp.dto.rest.createRestDto.ManufacturerCreateRest;
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
public class ManufacturerRestEndpointServiceImp implements ManufacturerRestEndpointService {

    private final Logger logger = LoggerFactory.getLogger(ManufacturerRestEndpointServiceImp.class);

    private final ManufacturerSoapService manufacturerSoapService;
    private final ItemSoapService itemSoapService;
    private final BrandSoapService brandSoapService;
    private final RestModelAssembler restModelAssembler;
    private final CastRestCreate castRestCreate;

    @Autowired
    public ManufacturerRestEndpointServiceImp(ManufacturerSoapService manufacturerSoapService, ItemSoapService itemSoapService, BrandSoapService brandSoapService, RestModelAssembler restModelAssembler, CastRestCreate castRestCreate) {
        this.manufacturerSoapService = manufacturerSoapService;
        this.itemSoapService = itemSoapService;
        this.brandSoapService = brandSoapService;
        this.restModelAssembler = restModelAssembler;
        this.castRestCreate = castRestCreate;
    }

    @Override
    public EntityModel<ManufacturerRest> getManufacturerById(String id) {
        try {
            ManufacturerCache manufacturer = manufacturerSoapService.getManufacturerById(Long.parseLong(id));
            return restModelAssembler.manufacturerToManufacturerRestModel(manufacturer);
        } catch (NumberFormatException e) {
            logger.error("Illegal URI argument = " + id + " Message: " + e.getLocalizedMessage());
            throw new BadRequestException("Illegal URI argument = " + id);
        }
    }

    @Override
    public EntityModel<ManufacturerRest> getManufacturerByName(String name) {
        ManufacturerCache manufacturer = manufacturerSoapService.getManufacturerByName(name);
        return restModelAssembler.manufacturerToManufacturerRestModel(manufacturer);
    }

    @Override
    public EntityModel<ManufacturerRest> createManufacturer(ManufacturerCreateRest manufacturerCreateRest) {
        ManufacturerCache manufacturer = castRestCreate.toManufacturer(manufacturerCreateRest);
        manufacturer = manufacturerSoapService.createManufacturer(manufacturer);
        return restModelAssembler.manufacturerToManufacturerRestModel(manufacturer);
    }

    @Override
    public EntityModel<ManufacturerRest> updateManufacturer(ManufacturerCreateRest manufacturerCreateRest) {
        ManufacturerCache manufacturer = castRestCreate.toManufacturer(manufacturerCreateRest);
        manufacturer = manufacturerSoapService.updateManufacturer(manufacturer);
        return restModelAssembler.manufacturerToManufacturerRestModel(manufacturer);
    }

    @Override
    public boolean deleteManufacturerById(String id) {
        try {
            return manufacturerSoapService.deleteManufacturerById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            logger.error("Illegal URI argument = " + id + " Message: " + e.getLocalizedMessage());
            throw new BadRequestException("Illegal URI argument = " + id);
        }
    }

    @Override
    public List<EntityModel<ManufacturerRest>> getAllManufacturers() {
        List<ManufacturerCache> results = manufacturerSoapService.getAllManufacturers();
        List<EntityModel<ManufacturerRest>> manufacturersRest = new LinkedList<>();
        results.forEach(m -> manufacturersRest.add(restModelAssembler.manufacturerToManufacturerRestModel(m)));
        return manufacturersRest;
    }

    @Override
    public List<EntityModel<ItemRest>> getAllItemsByManufacturerId(String id) {
        try {
            List<ItemCache> results = itemSoapService.getItemsByManufacturerId(Long.parseLong(id));
            List<EntityModel<ItemRest>> itemRest = new LinkedList<>();
            results.forEach(m -> itemRest.add(restModelAssembler.itemToItemRestModel(m)));
            return itemRest;
        } catch (NumberFormatException e) {
            logger.error("Illegal URI argument = " + id + " Message: " + e.getLocalizedMessage());
            throw new BadRequestException("Illegal URI argument = " + id);
        }
    }

    @Override
    public List<EntityModel<ItemRest>> getAllItemsByManufacturerName(String name) {
        List<ItemCache> results = itemSoapService.getItemsByManufacturerName(name);
        List<EntityModel<ItemRest>> itemRest = new LinkedList<>();
        results.forEach(m -> itemRest.add(restModelAssembler.itemToItemRestModel(m)));
        return itemRest;
    }

    @Override
    public List<EntityModel<BrandRest>> getAllBrandsByManufacturersId(String id) {
        try {
            List<BrandCache> results = brandSoapService.getBrandsByManufacturersId(Long.parseLong(id));
            List<EntityModel<BrandRest>> itemRest = new LinkedList<>();
            results.forEach(m -> itemRest.add(restModelAssembler.brandToBrandRestModel(m)));
            return itemRest;
        } catch (NumberFormatException e) {
            logger.error("Illegal URI argument = " + id + " Message: " + e.getLocalizedMessage());
            throw new BadRequestException("Illegal URI argument = " + id);
        }
    }

    @Override
    public List<EntityModel<BrandRest>> getAllBrandsByManufacturersName(String name) {
        List<BrandCache> results = brandSoapService.getBrandsByManufacturersName(name);
        List<EntityModel<BrandRest>> itemRest = new LinkedList<>();
        results.forEach(m -> itemRest.add(restModelAssembler.brandToBrandRestModel(m)));
        return itemRest;
    }

    @Override
    public List<EntityModel<ManufacturerRest>> getAllByAddressCity(String city) {
        List<ManufacturerCache> results = manufacturerSoapService.getManufacturersByAddressCity(city);
        List<EntityModel<ManufacturerRest>> manRest = new LinkedList<>();
        results.forEach(m -> manRest.add(restModelAssembler.manufacturerToManufacturerRestModel(m)));
        return manRest;
    }

    @Override
    public List<EntityModel<ManufacturerRest>> getAllByAddressCountry(String country) {
        List<ManufacturerCache> results = manufacturerSoapService.getManufacturersByAddressCountry(country);
        List<EntityModel<ManufacturerRest>> manRest = new LinkedList<>();
        results.forEach(m -> manRest.add(restModelAssembler.manufacturerToManufacturerRestModel(m)));
        return manRest;
    }
}
