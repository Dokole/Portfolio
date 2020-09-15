package com.transcacheservice.cacheapp.restController;

import com.transcacheservice.cacheapp.dto.rest.BrandRest;
import com.transcacheservice.cacheapp.dto.rest.ItemRest;
import com.transcacheservice.cacheapp.dto.rest.ManufacturerRest;
import com.transcacheservice.cacheapp.dto.rest.createRestDto.ManufacturerCreateRest;
import com.transcacheservice.cacheapp.service.restEndpointService.ManufacturerRestEndpointService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@RestController
@RequestMapping(value = "/api/v1/database/manufacturers")
public class ManufacturerControllerImp implements ManufacturerController{

    private final Logger logger = LoggerFactory.getLogger(ManufacturerControllerImp.class);

    private final ManufacturerRestEndpointService manufacturerRestEndpointService;

    @Autowired
    public ManufacturerControllerImp(ManufacturerRestEndpointService manufacturerRestEndpointService) {
        this.manufacturerRestEndpointService = manufacturerRestEndpointService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/getAll")
    public CollectionModel<EntityModel<ManufacturerRest>> getAllManufacturers() {
        return CollectionModel.of(manufacturerRestEndpointService.getAllManufacturers());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public EntityModel<ManufacturerRest> getManufacturerById(@PathVariable String id) {
        return manufacturerRestEndpointService.getManufacturerById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/name/{name}")
    public EntityModel<ManufacturerRest> getManufacturerByName(@PathVariable String name) {
        return manufacturerRestEndpointService.getManufacturerByName(name);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public EntityModel<ManufacturerRest> createManufacturer(@RequestBody ManufacturerCreateRest manufacturerCreateRest) {
        return manufacturerRestEndpointService.createManufacturer(manufacturerCreateRest);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EntityModel<ManufacturerRest> updateManufacturer(@RequestBody ManufacturerCreateRest manufacturerCreateRest, @PathVariable String id) {
        return manufacturerRestEndpointService.updateManufacturer(manufacturerCreateRest);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public boolean deleteManufacturerById(@PathVariable String id) {
        return manufacturerRestEndpointService.deleteManufacturerById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}/items")
    public CollectionModel<EntityModel<ItemRest>> getAllItemsByManufacturerId(@PathVariable String id) {
        return CollectionModel.of(manufacturerRestEndpointService.getAllItemsByManufacturerId(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/name/{name}/items")
    @Override
    public CollectionModel<EntityModel<ItemRest>> getAllItemsByManufacturerName(@PathVariable String name) {
        return CollectionModel.of(manufacturerRestEndpointService.getAllItemsByManufacturerName(name));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}/brands")
    public CollectionModel<EntityModel<BrandRest>> getAllBrandsByManufacturerId(@PathVariable String id) {
        return CollectionModel.of(manufacturerRestEndpointService.getAllBrandsByManufacturersId(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/name/{name}/brands")
    @Override
    public CollectionModel<EntityModel<BrandRest>> getAllBrandsByManufacturerName(@PathVariable String name) {
        return CollectionModel.of(manufacturerRestEndpointService.getAllBrandsByManufacturersName(name));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/address/city/{city}")
    public CollectionModel<EntityModel<ManufacturerRest>> getAllManufacturersByCity(@PathVariable String city) {
        return CollectionModel.of(manufacturerRestEndpointService.getAllByAddressCity(city));
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/address/country/{country}")
    public CollectionModel<EntityModel<ManufacturerRest>> getAllManufacturersByCountry(@PathVariable String country) {
        return CollectionModel.of(manufacturerRestEndpointService.getAllByAddressCountry(country));
    }

}
