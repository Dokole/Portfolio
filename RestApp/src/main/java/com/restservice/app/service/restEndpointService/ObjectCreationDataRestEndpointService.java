package com.restservice.app.service.restEndpointService;

import com.restservice.app.dto.rest.CreationDataRest;

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
