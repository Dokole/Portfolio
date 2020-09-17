package com.soapdataservice.app.endpoint;

import com.soapdataservice.app.dto.*;

public interface MetadataEndpoint {

    GetCreationDataResponse getItemCreation(GetItemCreationDataRequest request);

    GetCreationDataResponse getItemCreation(GetBrandCreationDataRequest request);

    GetCreationDataResponse getItemCreation(GetManufacturerCreationDataRequest request);

    GetCreationDataResponse getItemCreation(GetCategoryCreationDataRequest request);
}
