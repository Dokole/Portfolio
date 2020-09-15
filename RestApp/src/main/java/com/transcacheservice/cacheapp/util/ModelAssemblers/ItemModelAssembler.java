package com.transcacheservice.cacheapp.util.ModelAssemblers;

import com.transcacheservice.cacheapp.dto.rest.ItemRest;
import com.transcacheservice.cacheapp.restController.BrandControllerImp;
import com.transcacheservice.cacheapp.restController.CategoryControllerImp;
import com.transcacheservice.cacheapp.restController.ItemControllerImp;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ItemModelAssembler implements RepresentationModelAssembler<ItemRest, EntityModel<ItemRest>> {
    @Override
    public EntityModel<ItemRest> toModel(ItemRest entity) {
        String id = String.valueOf(entity.getId());
        return EntityModel.of(entity,
                linkTo(methodOn(ItemControllerImp.class).getItemById(id)).withSelfRel()
                        .andAffordance(afford(methodOn(BrandControllerImp.class).updateBrand(null, id))),
                linkTo(methodOn(ItemControllerImp.class).createItem(null)).withRel("create_new_item"),
//                linkTo(methodOn(ItemControllerImp.class).deleteItemById(id)).withRel("delete_this"),
                linkTo(methodOn(ItemControllerImp.class).getAllItems()).withRel("getAll"),
                linkTo(methodOn(ItemControllerImp.class).getBrandByItemId(id)).withRel("item_brand"),
                linkTo(methodOn(ItemControllerImp.class).getManufacturerByItemId(id)).withRel("item_manufacturers"),
                linkTo(methodOn(ItemControllerImp.class).getAllCategoriesByItemId(id)).withRel("item_categories"),
                linkTo(methodOn(ItemControllerImp.class).getItemsByDescription("")).withRel("items_by_description"),
                linkTo(methodOn(ItemControllerImp.class)
                        .getItemsByPriceBetween(0, entity.getPrice())).withRel("items_in_price_range"));
    }

}
