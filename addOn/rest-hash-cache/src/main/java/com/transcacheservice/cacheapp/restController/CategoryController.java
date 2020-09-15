package com.transcacheservice.cacheapp.restController;

import com.transcacheservice.cacheapp.domain.cache.redis.CategoryCache;
import com.transcacheservice.cacheapp.dto.rest.CategoryRest;
import com.transcacheservice.cacheapp.dto.rest.ItemRest;
import com.transcacheservice.cacheapp.dto.rest.createRestDto.CategoryCreateRest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import java.util.List;

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
