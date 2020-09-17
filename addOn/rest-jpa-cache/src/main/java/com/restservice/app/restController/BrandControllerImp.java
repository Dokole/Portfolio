package com.restservice.app.restController;

import com.restservice.app.dto.rest.BrandRest;
import com.restservice.app.dto.rest.ItemRest;
import com.restservice.app.dto.rest.ManufacturerRest;
import com.restservice.app.dto.rest.createRestDto.BrandCreateRest;
import com.restservice.app.service.restEndpointService.BrandRestEndpointService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */


@RestController
@RequestMapping(value = "/api/v1/database/brands")
public class BrandControllerImp implements BrandController {

    private final Logger logger = LoggerFactory.getLogger(BrandControllerImp.class);

    private final BrandRestEndpointService brandRestEndpointService;

    @Autowired
    public BrandControllerImp(BrandRestEndpointService brandRestEndpointService) {
        this.brandRestEndpointService = brandRestEndpointService;

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/getAll")
    public CollectionModel<EntityModel<BrandRest>> getAllBrands() {
        List<EntityModel<BrandRest>> brandRests = brandRestEndpointService.getAllBrands();
        return CollectionModel.of(brandRests);

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/name/{name}")
    public EntityModel<BrandRest> getBrandByName(@PathVariable String name) {
        return brandRestEndpointService.getBrandByName(name);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public EntityModel<BrandRest> getBrandById(@PathVariable String id) {
        return brandRestEndpointService.getBrandById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public EntityModel<BrandRest> createBrand(@RequestBody BrandCreateRest brandCreateRest) {
        System.out.println(brandCreateRest.getManufacturersIds().size());
        return brandRestEndpointService.createBrand(brandCreateRest);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EntityModel<BrandRest> updateBrand(@RequestBody BrandCreateRest brandCreateRest, @PathVariable String id) {
        System.out.println(brandCreateRest.getManufacturersIds().size());
        return brandRestEndpointService.updateBrand(brandCreateRest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    @Override
    public boolean deleteBrandById(@PathVariable String id) {
        return brandRestEndpointService.deleteBrandById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}/items")
    public CollectionModel<EntityModel<ItemRest>> getAllItemsByBrandId(@PathVariable String id) {
        return CollectionModel.of(brandRestEndpointService.getAllItemsByBrandId(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/name/{name}/items")
    public CollectionModel<EntityModel<ItemRest>> getAllItemsByBrandName(@PathVariable String name) {
        return CollectionModel.of(brandRestEndpointService.getAllItemsByBrandName(name));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}/manufacturers")
    public CollectionModel<EntityModel<ManufacturerRest>> getAllManufacturersByBrandId(@PathVariable String id) {
        return CollectionModel.of(brandRestEndpointService.getAllManufacturersByBrandId(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/name/{name}/manufacturers")
    public CollectionModel<EntityModel<ManufacturerRest>> getAllManufacturersByBrandName(@PathVariable String name) {
        return CollectionModel.of(brandRestEndpointService.getAllManufacturersByBrandsName(name));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/address/city/{city}")
    public CollectionModel<EntityModel<BrandRest>> getAllBrandsByAddressCity(@PathVariable String city) {
        return CollectionModel.of(brandRestEndpointService.getAllBrandsByAddressCity(city));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/address/country/{country}")
    public CollectionModel<EntityModel<BrandRest>> getAllBrandsByAddressCountry(@PathVariable String country) {
        return CollectionModel.of(brandRestEndpointService.getAllBrandsByAddressCountry(country));
    }

}
