package com.transcacheservice.cacheapp.service.restEndpointService;

import com.transcacheservice.cacheapp.dto.rest.CategoryRest;
import com.transcacheservice.cacheapp.dto.rest.ItemRest;
import com.transcacheservice.cacheapp.dto.rest.createRestDto.CategoryCreateRest;
import com.transcacheservice.cacheapp.domain.cache.redis.CategoryCache;
import com.transcacheservice.cacheapp.domain.cache.redis.ItemCache;
import com.transcacheservice.cacheapp.exceptions.BadRequestException;
import com.transcacheservice.cacheapp.service.soapService.CategorySoapService;
import com.transcacheservice.cacheapp.service.soapService.ItemSoapService;
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

    private final CategorySoapService categorySoapService;
    private final ItemSoapService itemSoapService;
    private final RestModelAssembler restModelAssembler;
    private final CastRestCreate castRestCreate;

    @Autowired
    public CategoryRestEndpointServiceImp(CategorySoapService categorySoapService, ItemSoapService itemSoapService, RestModelAssembler restModelAssembler, CastRestCreate castRestCreate) {
        this.categorySoapService = categorySoapService;
        this.itemSoapService = itemSoapService;
        this.restModelAssembler = restModelAssembler;
        this.castRestCreate = castRestCreate;
    }

    @Override
    public EntityModel<CategoryRest> getCategoryById(String id) {
        try {
            CategoryCache category = categorySoapService.getCategoryById(Long.parseLong(id));
            return restModelAssembler.categoryToCategoryRestModel(category);
        } catch (NumberFormatException e) {
            logger.error("Illegal URI argument = " + id + " Message: " + e.getLocalizedMessage());
            throw new BadRequestException("Illegal URI argument = " + id);
        }
    }

    @Override
    public EntityModel<CategoryRest> createCategory(CategoryCreateRest categoryCreateRest) {
        CategoryCache category = castRestCreate.toCategory(categoryCreateRest);
        category = categorySoapService.createCategory(category);
        return restModelAssembler.categoryToCategoryRestModel(category);
    }

    @Override
    public EntityModel<CategoryRest> updateCategory(CategoryCreateRest categoryCreateRest) {
        CategoryCache category = castRestCreate.toCategory(categoryCreateRest);
        category = categorySoapService.updateCategory(category);
        return restModelAssembler.categoryToCategoryRestModel(category);
    }

    @Override
    public boolean deleteCategoryById(String id) {
        try {
            return categorySoapService.deleteCategoryById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            logger.error("Illegal URI argument = " + id + " Message: " + e.getLocalizedMessage());
            throw new BadRequestException("Illegal URI argument = " + id);
        }
    }

    @Override
    public List<EntityModel<CategoryRest>> getAllCategories() {
        List<CategoryCache> results = categorySoapService.getAllCategories();
        List<EntityModel<CategoryRest>> categoriesRest = new LinkedList<>();
        results.forEach(c -> categoriesRest.add(restModelAssembler.categoryToCategoryRestModel(c)));
        return categoriesRest;
    }

    @Override
    public List<EntityModel<ItemRest>> getAllItemsByCategoryId(String id) {
        try {
            List<ItemCache> items = itemSoapService.getItemsByCategoriesId(Long.parseLong(id));
            List<EntityModel<ItemRest>> itemsRest = new LinkedList<>();
            items.forEach(i -> itemsRest.add(restModelAssembler.itemToItemRestModel(i)));
            return itemsRest;
        } catch (NumberFormatException e) {
            logger.error("Illegal URI argument = " + id + " Message: " + e.getLocalizedMessage());
            throw new BadRequestException("Illegal URI argument = " + id);
        }
    }

    @Override
    public EntityModel<CategoryRest> getCategoryByName(String name) {
        CategoryCache category = categorySoapService.getCategoryByName(name);
        return restModelAssembler.categoryToCategoryRestModel(category);
    }

    @Override
    public List<EntityModel<ItemRest>> getAllItemsByCategoryName(String name) {
        List<ItemCache> items = itemSoapService.getItemsByCategoriesName(name);
        List<EntityModel<ItemRest>> itemsRest = new LinkedList<>();
        items.forEach(i -> itemsRest.add(restModelAssembler.itemToItemRestModel(i)));
        return itemsRest;
    }
}
