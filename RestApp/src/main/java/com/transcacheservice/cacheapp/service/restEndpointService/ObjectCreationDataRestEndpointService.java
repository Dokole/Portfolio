package com.transcacheservice.cacheapp.service.restEndpointService;

import com.transcacheservice.cacheapp.dto.rest.CreationDataRest;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public interface ObjectCreationDataRestEndpointService {

    CreationDataRest getBrandsMetadata();
    CreationDataRest getCategoriesMetadata();
    CreationDataRest getManufacturersMetadata();
    CreationDataRest getItemsMetadata();
}
