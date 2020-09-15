package com.transcacheservice.cacheapp.restController;

import com.transcacheservice.cacheapp.dto.rest.CreationDataRest;
import com.transcacheservice.cacheapp.service.restEndpointService.ObjectCreationDataRestEndpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@RestController
@RequestMapping(value = "/api/v1/database")
public class CreationDataController {

    private final ObjectCreationDataRestEndpointService objectCreationDataRestEndpointService;

    @Autowired
    public CreationDataController(ObjectCreationDataRestEndpointService objectCreationDataRestEndpointService) {
        this.objectCreationDataRestEndpointService = objectCreationDataRestEndpointService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/items/creationData")
    public CreationDataRest getItemMetadata() {
        return objectCreationDataRestEndpointService.getItemsMetadata();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/brands/creationData")
    public CreationDataRest getBrandsMetadata() {
        return objectCreationDataRestEndpointService.getBrandsMetadata();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/categories/creationData")
    public CreationDataRest getCategoriesMetadata() {
        return objectCreationDataRestEndpointService.getCategoriesMetadata();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/manufacturers/creationData")
    public CreationDataRest getManufacturersMetadata() {
        return objectCreationDataRestEndpointService.getManufacturersMetadata();
    }

}
