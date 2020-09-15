package com.transcacheservice.cacheapp.util.ModelAssemblers;

import com.transcacheservice.cacheapp.dto.rest.CategoryRest;
import com.transcacheservice.cacheapp.restController.BrandControllerImp;
import com.transcacheservice.cacheapp.restController.CategoryControllerImp;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Component
public class CategoryModelAssembler implements RepresentationModelAssembler<CategoryRest, EntityModel<CategoryRest>> {
    @Override
    public EntityModel<CategoryRest> toModel(CategoryRest entity) {
        String id = String.valueOf(entity.getId());
        return EntityModel.of(entity,
                linkTo(methodOn(CategoryControllerImp.class).getCategoryById(id)).withSelfRel()
                        .andAffordance(afford(methodOn(BrandControllerImp.class).updateBrand(null, id))),
                linkTo(methodOn(CategoryControllerImp.class).createCategory(null)).withRel("create_new_category"),
//                linkTo(methodOn(CategoryControllerImp.class).deleteCategoryById(id)).withRel("delete_this"),
                linkTo(methodOn(CategoryControllerImp.class).getAllCategories()).withRel("getAll"),
                linkTo(methodOn(CategoryControllerImp.class).getAllItemsByCategoryId(id)).withRel("category_items"));
    }
}
