package com.restservice.app.util.ModelAssemblers;

import com.restservice.app.restController.BrandControllerImp;
import com.restservice.app.restController.CategoryControllerImp;
import com.restservice.app.dto.rest.CategoryRest;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
                WebMvcLinkBuilder.linkTo(methodOn(CategoryControllerImp.class).getCategoryById(id)).withSelfRel()
                        .andAffordance(afford(methodOn(BrandControllerImp.class).updateBrand(null, id))),
                linkTo(methodOn(CategoryControllerImp.class).createCategory(null)).withRel("create_new_category"),
//                linkTo(methodOn(CategoryControllerImp.class).deleteCategoryById(id)).withRel("delete_this"),
                linkTo(methodOn(CategoryControllerImp.class).getAllCategories()).withRel("getAll"),
                linkTo(methodOn(CategoryControllerImp.class).getAllItemsByCategoryId(id)).withRel("category_items"));
    }
}
