package com.soapdataservice.app.endpoint;

import com.soapdataservice.app.dto.*;

public interface ManufacturerEndpoint {

    GetManufacturerByIdResponse getManufacturerById(GetManufacturerByIdRequest request);

    GetManufacturerByNameResponse getManufacturerByName(GetManufacturerByNameRequest request);

    CreateManufacturerResponse createManufacturer(CreateManufacturerRequest request);

    UpdateManufacturerResponse updateManufacturer(UpdateManufacturerRequest request);

    DeleteManufacturerByIdResponse deleteManufacturerById(DeleteManufacturerByIdRequest request);

    GetAllManufacturersResponse getAllManufacturers(GetAllManufacturersRequest request);

    GetManufacturerByItemIdResponse getManufacturerByItemId(GetManufacturerByItemIdRequest request);

    GetManufacturerByItemNameResponse getManufacturerByItemName(GetManufacturerByItemNameRequest request);

    GetAllManufacturersByCityResponse getAllManufacturersByCity(GetAllManufacturersByCityRequest request);

    GetAllManufacturersByCountryResponse getAllManufacturersByCountry(GetAllManufacturersByCountryRequest request);

    GetAllManufacturersByBrandIdResponse getAllManufacturersByBrandId(GetAllManufacturersByBrandIdRequest request);

    GetAllManufacturersByBrandNameResponse getAllManufacturersByBrandName(GetAllManufacturersByBrandNameRequest request);
}
