package com.producingservice.app.endpoint;

import database.dto.app.producingservice.com.*;

public interface MetadataEndpoint {

    GetCreationDataResponse getItemCreation(GetItemCreationDataRequest request);

    GetCreationDataResponse getItemCreation(GetBrandCreationDataRequest request);

    GetCreationDataResponse getItemCreation(GetManufacturerCreationDataRequest request);

    GetCreationDataResponse getItemCreation(GetCategoryCreationDataRequest request);
}
