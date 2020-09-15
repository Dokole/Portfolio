package com.transcacheservice.cacheapp.service.databaseService;


import com.transcacheservice.cacheapp.dto.CreationData;

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

