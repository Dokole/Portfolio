package com.restservice.app.restController;

import com.restservice.app.dto.rest.CategoryRest;
import com.restservice.app.dto.rest.ItemRest;
import com.restservice.app.dto.rest.createRestDto.CategoryCreateRest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public interface CategoryController {

    EntityModel<CategoryRest> getCategoryById(String id);
    EntityModel<CategoryRest> getCategoryByName(String name);

    EntityModel<CategoryRest>  createCategory(CategoryCreateRest categoryCreateRest);
    EntityModel<CategoryRest>  updateCategory(CategoryCreateRest categoryCreateRest, String id);

    boolean deleteCategoryById(String id);

    CollectionModel<EntityModel<CategoryRest>> getAllCategories();

    CollectionModel<EntityModel<ItemRest>> getAllItemsByCategoryId(String id);
    CollectionModel<EntityModel<ItemRest>> getAllItemsByCategoryName(String name);
}
