package com.restservice.app.restController;

import com.restservice.app.dto.rest.BrandRest;
import com.restservice.app.dto.rest.CategoryRest;
import com.restservice.app.dto.rest.ItemRest;
import com.restservice.app.dto.rest.ManufacturerRest;
import com.restservice.app.dto.rest.createRestDto.ItemCreateRest;
import com.restservice.app.service.restEndpointService.ItemRestEndpointService;
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
@RequestMapping(value = "/api/v1/database/items")
public class ItemControllerImp implements ItemController{

    private final Logger logger = LoggerFactory.getLogger(ItemControllerImp.class);
    private final ItemRestEndpointService itemRestEndpointService;

    @Autowired
    public ItemControllerImp(ItemRestEndpointService itemRestEndpointService) {
        this.itemRestEndpointService = itemRestEndpointService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/getAll")
    public CollectionModel<EntityModel<ItemRest>> getAllItems() {
        return CollectionModel.of(itemRestEndpointService.getAllItems());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public EntityModel<ItemRest> getItemById(@PathVariable String id) {
        return itemRestEndpointService.getItemById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/name/{name}")
    @Override
    public EntityModel<ItemRest> getItemByName(@PathVariable String name) {
        return itemRestEndpointService.getItemByName(name);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public EntityModel<ItemRest> createItem(@RequestBody ItemCreateRest itemCreateRest) {
        return itemRestEndpointService.createItem(itemCreateRest);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EntityModel<ItemRest> updateItem(@RequestBody ItemCreateRest itemCreateRest, @PathVariable String id) {
        return itemRestEndpointService.updateItem(itemCreateRest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public boolean deleteItemById(@PathVariable String id) {
        return itemRestEndpointService.deleteItemById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}/brand")
    @Override
    public EntityModel<BrandRest> getBrandByItemId(@PathVariable String id) {
        return itemRestEndpointService.getBrandByItemId(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/name/{name}/brand")
    @Override
    public EntityModel<BrandRest> getBrandByItemName(@PathVariable String name) {
        return itemRestEndpointService.getBrandByItemName(name);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}/manufacturer")
    @Override
    public EntityModel<ManufacturerRest> getManufacturerByItemId(@PathVariable String id) {
        return itemRestEndpointService.getManufacturerByItemId(id);

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/name/{name}/manufacturer")
    @Override
    public EntityModel<ManufacturerRest> getManufacturerByItemName(@PathVariable String name) {
        return itemRestEndpointService.getManufacturerByItemName(name);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}/categories")
    @Override
    public CollectionModel<EntityModel<CategoryRest>> getAllCategoriesByItemId(@PathVariable String id) {
        return CollectionModel.of(itemRestEndpointService.getAllCategoriesByItemId(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/name/{name}/categories")
    @Override
    public CollectionModel<EntityModel<CategoryRest>> getAllCategoriesByItemName(@PathVariable String name) {
        return CollectionModel.of(itemRestEndpointService.getAllCategoriesByItemName(name));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/description/{desc}")
    @Override
    public CollectionModel<EntityModel<ItemRest>> getItemsByDescription(@PathVariable String desc) {
        return CollectionModel.of(itemRestEndpointService.getItemsByDescription(desc));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/priceInRange")
    @Override
    public CollectionModel<EntityModel<ItemRest>> getItemsByPriceBetween(@RequestParam int low, @RequestParam int high) {
        return CollectionModel.of(itemRestEndpointService.getItemsByPriceBetween(low, high));
    }
}
