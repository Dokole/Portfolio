package com.soapdataservice.app.endpoint;

import com.soapdataservice.app.dto.*;

public interface BrandEndpoint {

    GetBrandByIdResponse getBrandById(GetBrandByIdRequest request);

    GetBrandByNameResponse getBrandByName(GetBrandByNameRequest request);

    CreateBrandResponse createBrand(CreateBrandRequest request);

    UpdateBrandResponse updateBrand(UpdateBrandRequest request);

    DeleteBrandByIdResponse deleteBrandById(DeleteBrandByIdRequest request);

    GetBrandByItemIdResponse getBrandByItemId(GetBrandByItemIdRequest request);

    GetBrandByItemNameResponse getBrandByItemName(GetBrandByItemNameRequest request);

    GetAllBrandsResponse getAllBrands(GetAllBrandsRequest request);

    GetAllBrandsByCityResponse getAllBrandsByCity(GetAllBrandsByCityRequest request);

    GetAllBrandsByCountryResponse getAllBrandsByCountry(GetAllBrandsByCountryRequest request);

    GetAllBrandsByManufacturerIdResponse getAllBrandsByManufacturerId(GetAllBrandsByManufacturerIdRequest request);

    GetAllBrandsByManufacturerNameResponse getAllBrandsByManufacturerName(GetAllBrandsByManufacturerNameRequest request);

}
