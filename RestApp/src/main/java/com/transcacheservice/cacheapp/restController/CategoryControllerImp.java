package com.transcacheservice.cacheapp.restController;


import com.transcacheservice.cacheapp.dto.rest.CategoryRest;
import com.transcacheservice.cacheapp.dto.rest.ItemRest;
import com.transcacheservice.cacheapp.dto.rest.createRestDto.CategoryCreateRest;
import com.transcacheservice.cacheapp.service.restEndpointService.CategoryRestEndpointService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@RestController
@RequestMapping(value = "/api/v1/database/categories")
public class CategoryControllerImp implements CategoryController {

    private final Logger logger = LoggerFactory.getLogger(CategoryControllerImp.class);
    private final CategoryRestEndpointService categoryRestEndpointService;

    @Autowired
    public CategoryControllerImp(CategoryRestEndpointService CategoryRestEndpointService) {
        this.categoryRestEndpointService = CategoryRestEndpointService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/getAll")
    public CollectionModel<EntityModel<CategoryRest>> getAllCategories() {
        return CollectionModel.of(categoryRestEndpointService.getAllCategories());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public EntityModel<CategoryRest> getCategoryById(@PathVariable String id) {
        return categoryRestEndpointService.getCategoryById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/name/{name}")
    @Override
    public EntityModel<CategoryRest> getCategoryByName(@PathVariable String name) {
        return categoryRestEndpointService.getCategoryByName(name);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public EntityModel<CategoryRest> createCategory(@RequestBody CategoryCreateRest categoryCreateRest) {
        return categoryRestEndpointService.createCategory(categoryCreateRest);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EntityModel<CategoryRest> updateCategory(@RequestBody CategoryCreateRest categoryCreateRest, @PathVariable String id) {
        return categoryRestEndpointService.updateCategory(categoryCreateRest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}/items")
    public CollectionModel<EntityModel<ItemRest>> getAllItemsByCategoryId(@PathVariable String id) {
        return CollectionModel.of(categoryRestEndpointService.getAllItemsByCategoryId(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/name/{name}/items")
    @Override
    public CollectionModel<EntityModel<ItemRest>> getAllItemsByCategoryName(@PathVariable String name) {
        return CollectionModel.of(categoryRestEndpointService.getAllItemsByCategoryName(name));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public boolean deleteCategoryById(@PathVariable String id) {
        return categoryRestEndpointService.deleteCategoryById(id);
    }
}
