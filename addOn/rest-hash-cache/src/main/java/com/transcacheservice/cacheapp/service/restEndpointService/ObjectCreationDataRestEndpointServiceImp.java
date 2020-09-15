package com.transcacheservice.cacheapp.service.restEndpointService;

import com.transcacheservice.cacheapp.dto.rest.CreationDataRest;
import com.transcacheservice.cacheapp.service.databaseService.ObjectCreationDataDatabaseService;
import com.transcacheservice.cacheapp.util.ModelAssemblers.RestModelAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Service
public class ObjectCreationDataRestEndpointServiceImp implements ObjectCreationDataRestEndpointService {

    private final Logger logger = LoggerFactory.getLogger(ObjectCreationDataRestEndpointServiceImp.class);

    private final ObjectCreationDataDatabaseService objectCreationDataDatabaseService;
    private final RestModelAssembler restModelAssembler;
    @Autowired
    public ObjectCreationDataRestEndpointServiceImp(ObjectCreationDataDatabaseService objectCreationDataDatabaseService, RestModelAssembler restModelAssembler) {
        this.objectCreationDataDatabaseService = objectCreationDataDatabaseService;
        this.restModelAssembler = restModelAssembler;
    }

    @Override
    public CreationDataRest getBrandsMetadata() {
        return restModelAssembler.creationDataToCreationDataRest(objectCreationDataDatabaseService.getBrandsMetadata());
    }

    @Override
    public CreationDataRest getCategoriesMetadata() {
        return restModelAssembler.creationDataToCreationDataRest(objectCreationDataDatabaseService.getCategoriesMetadata());
    }

    @Override
    public CreationDataRest getManufacturersMetadata() {
        return restModelAssembler.creationDataToCreationDataRest(objectCreationDataDatabaseService.getManufacturersMetadata());
    }

    @Override
    public CreationDataRest getItemsMetadata() {
        return restModelAssembler.creationDataToCreationDataRest(objectCreationDataDatabaseService.getItemsMetadata());
    }
}
