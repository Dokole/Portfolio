package com.restservice.app.util.ModelAssemblers;

import com.restservice.app.restController.BrandControllerImp;
import com.restservice.app.dto.rest.BrandRest;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Component
public class BrandModelAssembler implements RepresentationModelAssembler<BrandRest, EntityModel<BrandRest>> {
    @Override
    public EntityModel<BrandRest> toModel(BrandRest entity) {
        String id = String.valueOf(entity.getId());
        return EntityModel.of(entity,
                WebMvcLinkBuilder.linkTo(methodOn(BrandControllerImp.class).getBrandById(id)).withSelfRel()
                        .andAffordance(afford(methodOn(BrandControllerImp.class).updateBrand(null, id))),
                linkTo(methodOn(BrandControllerImp.class).createBrand(null)).withRel("create_new_brand"),
//                linkTo(methodOn(BrandControllerImp.class).deleteBrandById(id)).withRel("delete_this"),
                linkTo(methodOn(BrandControllerImp.class).getAllBrands()).withRel("getAll"),
                linkTo(methodOn(BrandControllerImp.class).getAllItemsByBrandId(id)).withRel("brand_items"),
                linkTo(methodOn(BrandControllerImp.class).getAllManufacturersByBrandId(id)).withRel("brand_manufacturers"),
                linkTo(methodOn(BrandControllerImp.class).getAllBrandsByAddressCountry(entity.getAddress().getCountry())).withRel("brand_country"),
                linkTo(methodOn(BrandControllerImp.class).getAllBrandsByAddressCity(entity.getAddress().getCity())).withRel("brand_city"));
    }
}
