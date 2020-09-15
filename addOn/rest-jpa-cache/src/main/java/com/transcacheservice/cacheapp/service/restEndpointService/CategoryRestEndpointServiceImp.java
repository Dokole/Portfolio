package com.transcacheservice.cacheapp.service.restEndpointService;

import com.transcacheservice.cacheapp.dto.rest.CategoryRest;
import com.transcacheservice.cacheapp.dto.rest.ItemRest;
import com.transcacheservice.cacheapp.dto.rest.createRestDto.CategoryCreateRest;
import com.transcacheservice.cacheapp.domain.cache.redis.CategoryCache;
import com.transcacheservice.cacheapp.domain.cache.redis.ItemCache;
import com.transcacheservice.cacheapp.service.databaseService.CategoryDatabaseService;
import com.transcacheservice.cacheapp.service.databaseService.ItemDatabaseService;
import com.transcacheservice.cacheapp.util.CastRestCreate;
import com.transcacheservice.cacheapp.util.ModelAssemblers.RestModelAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Service
public class CategoryRestEndpointServiceImp implements CategoryRestEndpointService {

    private final Logger logger = LoggerFactory.getLogger(CategoryRestEndpointServiceImp.class);

    private final CategoryDatabaseService categoryDatabaseService;
    private final ItemDatabaseService itemDatabaseService;
    private final RestModelAssembler restModelAssembler;
    private final CastRestCreate castRestCreate;

    @Autowired
    public CategoryRestEndpointServiceImp(CategoryDatabaseService categoryDatabaseService, ItemDatabaseService itemDatabaseService, RestModelAssembler restModelAssembler, CastRestCreate castRestCreate) {
        this.categoryDatabaseService = categoryDatabaseService;
        this.itemDatabaseService = itemDatabaseService;
        this.restModelAssembler = restModelAssembler;
        this.castRestCreate = castRestCreate;
    }

    @Override
    public EntityModel<CategoryRest> getCategoryById(String id) {
        CategoryCache category = categoryDatabaseService.getCategoryById(id);
        return restModelAssembler.categoryToCategoryRestModel(category);
    }

    @Override
    public EntityModel<CategoryRest> createCategory(CategoryCreateRest categoryCreateRest) {
        CategoryCache category = castRestCreate.toCategory(categoryCreateRest);
        category = categoryDatabaseService.createCategory(category);
        return restModelAssembler.categoryToCategoryRestModel(category);
    }

    @Override
    public EntityModel<CategoryRest> updateCategory(CategoryCreateRest categoryCreateRest) {
        CategoryCache category = castRestCreate.toCategory(categoryCreateRest);
        category = categoryDatabaseService.updateCategory(category);
        return restModelAssembler.categoryToCategoryRestModel(category);
    }

    @Override
    public boolean deleteCategoryById(String id) {
        return categoryDatabaseService.deleteCategoryById(id);
    }

    @Override
    public List<EntityModel<CategoryRest>> getAllCategories() {
        List<CategoryCache> results = categoryDatabaseService.getAllCategories();
        List<EntityModel<CategoryRest>> categoriesRest = new LinkedList<>();
        results.forEach(c -> categoriesRest.add(restModelAssembler.categoryToCategoryRestModel(c)));
        return categoriesRest;
    }

    @Override
    public List<EntityModel<ItemRest>> getAllItemsByCategoryId(String id) {
        List<ItemCache> items = itemDatabaseService.getAllItemsByCategoriesId(id);
        List<EntityModel<ItemRest>> itemsRest = new LinkedList<>();
        items.forEach(i -> itemsRest.add(restModelAssembler.itemToItemRestModel(i)));
        return itemsRest;
    }

    @Override
    public EntityModel<CategoryRest> getCategoryByName(String name) {
        CategoryCache category = categoryDatabaseService.getCategoryByName(name);
        return restModelAssembler.categoryToCategoryRestModel(category);
    }

    @Override
    public List<EntityModel<ItemRest>> getAllItemsByCategoryName(String name) {
        List<ItemCache> items = itemDatabaseService.getAllItemsByCategoriesName(name);
        List<EntityModel<ItemRest>> itemsRest = new LinkedList<>();
        items.forEach(i -> itemsRest.add(restModelAssembler.itemToItemRestModel(i)));
        return itemsRest;
    }
}
