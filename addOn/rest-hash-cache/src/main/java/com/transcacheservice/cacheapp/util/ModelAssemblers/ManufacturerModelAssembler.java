package com.transcacheservice.cacheapp.util.ModelAssemblers;

import com.transcacheservice.cacheapp.dto.rest.ManufacturerRest;
import com.transcacheservice.cacheapp.restController.BrandControllerImp;
import com.transcacheservice.cacheapp.restController.ManufacturerControllerImp;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ManufacturerModelAssembler implements RepresentationModelAssembler<ManufacturerRest, EntityModel<ManufacturerRest>> {
    @Override
    public EntityModel<ManufacturerRest> toModel(ManufacturerRest entity) {
        String id = String.valueOf(entity.getId());
        return EntityModel.of(entity,
                linkTo(methodOn(ManufacturerControllerImp.class).getManufacturerById(id)).withSelfRel()
                        .andAffordance(afford(methodOn(BrandControllerImp.class).updateBrand(null, id))),
                linkTo(methodOn(ManufacturerControllerImp.class).createManufacturer(null)).withRel("create_new_manufacturer"),
//                linkTo(methodOn(ManufacturerControllerImp.class).deleteManufacturerById(id)).withRel("delete_this"),
                linkTo(methodOn(ManufacturerControllerImp.class).getAllManufacturers()).withRel("getAll"),
                linkTo(methodOn(ManufacturerControllerImp.class).getAllItemsByManufacturerId(id)).withRel("manufacturer_items"),
                linkTo(methodOn(ManufacturerControllerImp.class).getAllBrandsByManufacturerId(id)).withRel("manufacturer_brands"),
                linkTo(methodOn(ManufacturerControllerImp.class).getAllManufacturersByCountry(entity.getAddress().getCountry())).withRel("manufacturer_country"),
                linkTo(methodOn(ManufacturerControllerImp.class).getAllManufacturersByCity(entity.getAddress().getCity())).withRel("manufacturer_city"));
    }
}
