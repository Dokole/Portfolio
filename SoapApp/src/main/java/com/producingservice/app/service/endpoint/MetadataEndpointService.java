package com.producingservice.app.service.endpoint;


import database.dto.app.producingservice.com.CreateDataDto;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public interface MetadataEndpointService {

    CreateDataDto getForItemCreation();
    CreateDataDto getForBrandCreation();
    CreateDataDto getForManufacturerCreation();
    CreateDataDto getForCategoryCreation();

}
