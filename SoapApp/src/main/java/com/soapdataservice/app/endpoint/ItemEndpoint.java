package com.soapdataservice.app.endpoint;

import com.soapdataservice.app.dto.*;

public interface ItemEndpoint {

    GetItemByIdResponse getItemById(GetItemByIdRequest request);

    GetItemByNameResponse getItemByName(GetItemByNameRequest request);

    CreateItemResponse createItem(CreateItemRequest request);

    UpdateItemResponse updateItem(UpdateItemRequest request);

    DeleteItemByIdResponse deleteItemById(DeleteItemByIdRequest request);

    GetAllItemsResponse getAllItems(GetAllItemsRequest request);

    GetItemsByBrandIdResponse getItemsByBrandId(GetItemsByBrandIdRequest request);

    GetItemsByManufacturerIdResponse getItemsByManufacturerId(GetItemsByManufacturerIdRequest request);

    GetItemsByCategoryIdResponse getItemsByCategoryId(GetItemsByCategoryIdRequest request);

    GetItemsByBrandNameResponse getItemsByBrandName(GetItemsByBrandNameRequest request);

    GetItemsByManufacturerNameResponse getItemsByManufacturerName(GetItemsByManufacturerNameRequest request);

    GetItemsByCategoryNameResponse getItemsByCategoryName(GetItemsByCategoryNameRequest request);

    GetItemsByDescriptionResponse getItemsByDescription(GetItemsByDescriptionRequest request);

    GetItemsByPriceRangeResponse getItemsByPriceRange(GetItemsByPriceRangeRequest request);
}
