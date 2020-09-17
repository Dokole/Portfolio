package com.soapdataservice.app.endpoint;

import com.soapdataservice.app.dto.*;

public interface CategoryEndpoint {

    GetCategoryByIdResponse getCategoryById(GetCategoryByIdRequest request);

    GetCategoryByNameResponse getCategoryByName(GetCategoryByNameRequest request);

    CreateCategoryResponse createCategory(CreateCategoryRequest request);

    UpdateCategoryResponse updateCategory(UpdateCategoryRequest request);

    DeleteCategoryByIdResponse deleteCategoryById(DeleteCategoryByIdRequest request);

    GetAllCategoriesResponse getAllCategories(GetAllCategoriesRequest request);

    GetAllCategoriesByItemIdResponse getAllCategoriesByItemId(GetAllCategoriesByItemIdRequest request);

    GetAllCategoriesByItemNameResponse getAllCategoriesByItemName(GetAllCategoriesByItemNameRequest request);
}
