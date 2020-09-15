package com.transcacheservice.cacheapp.service.restEndpointService;

import com.transcacheservice.cacheapp.dto.rest.CategoryRest;
import com.transcacheservice.cacheapp.dto.rest.ItemRest;
import com.transcacheservice.cacheapp.dto.rest.createRestDto.CategoryCreateRest;
import org.springframework.hateoas.EntityModel;

import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public interface CategoryRestEndpointService {

    EntityModel<CategoryRest> getCategoryById(String id);

    EntityModel<CategoryRest> getCategoryByName(String name);

    EntityModel<CategoryRest>  createCategory(CategoryCreateRest categoryCreateRest);

    EntityModel<CategoryRest>  updateCategory(CategoryCreateRest categoryCreateRest);

    boolean deleteCategoryById(String id);

    List<EntityModel<CategoryRest>> getAllCategories();

    List<EntityModel<ItemRest>> getAllItemsByCategoryId(String id);

    List<EntityModel<ItemRest>> getAllItemsByCategoryName(String name);
}
