package com.restservice.app.service.databaseService;


import com.restservice.app.dto.CreationData;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public interface ObjectCreationDataDatabaseService {
    CreationData getBrandsMetadata();
    CreationData getCategoriesMetadata();
    CreationData getManufacturersMetadata();
    CreationData getItemsMetadata();

}

