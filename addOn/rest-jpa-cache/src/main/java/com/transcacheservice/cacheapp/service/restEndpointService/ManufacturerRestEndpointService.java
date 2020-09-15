package com.transcacheservice.cacheapp.service.restEndpointService;

import com.transcacheservice.cacheapp.domain.cache.redis.ManufacturerCache;
import com.transcacheservice.cacheapp.dto.rest.BrandRest;
import com.transcacheservice.cacheapp.dto.rest.ItemRest;
import com.transcacheservice.cacheapp.dto.rest.ManufacturerRest;
import com.transcacheservice.cacheapp.dto.rest.createRestDto.ManufacturerCreateRest;
import org.springframework.hateoas.EntityModel;

import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public interface ManufacturerRestEndpointService {

    EntityModel<ManufacturerRest> getManufacturerById(String id);

    EntityModel<ManufacturerRest> getManufacturerByName(String name);

    EntityModel<ManufacturerRest> createManufacturer(ManufacturerCreateRest manufacturerCreateRest);

    EntityModel<ManufacturerRest> updateManufacturer(ManufacturerCreateRest manufacturerCreateRest);

    boolean deleteManufacturerById(String id);

    List<EntityModel<ManufacturerRest>> getAllManufacturers();

    List<EntityModel<ItemRest>> getAllItemsByManufacturerId(String id);
    List<EntityModel<ItemRest>> getAllItemsByManufacturerName(String name);

    List<EntityModel<BrandRest>> getAllBrandsByManufacturersId(String id);
    List<EntityModel<BrandRest>> getAllBrandsByManufacturersName(String name);

    List<EntityModel<ManufacturerRest>> getAllByAddressCity(String city);
    List<EntityModel<ManufacturerRest>> getAllByAddressCountry(String country);


}
