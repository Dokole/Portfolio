package com.soapdataservice.app.service.endpoint;


import com.soapdataservice.app.dto.*;

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
