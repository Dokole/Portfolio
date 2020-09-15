package com.transcacheservice.cacheapp.service.restEndpointService;

import com.transcacheservice.cacheapp.domain.cache.redis.BrandCache;
import com.transcacheservice.cacheapp.dto.rest.BrandRest;
import com.transcacheservice.cacheapp.dto.rest.ItemRest;
import com.transcacheservice.cacheapp.dto.rest.ManufacturerRest;
import com.transcacheservice.cacheapp.dto.rest.createRestDto.BrandCreateRest;
import org.springframework.hateoas.EntityModel;

import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public interface BrandRestEndpointService {

    EntityModel<BrandRest> getBrandById(String id);

    EntityModel<BrandRest> getBrandByName(String name);

    EntityModel<BrandRest> createBrand(BrandCreateRest brandCreateRest);

    EntityModel<BrandRest> updateBrand(BrandCreateRest brandCreateRest);

    boolean deleteBrandById(String id);

    List<EntityModel<BrandRest>> getAllBrands();

    List<EntityModel<ItemRest>> getAllItemsByBrandId(String id);

    List<EntityModel<ManufacturerRest>> getAllManufacturersByBrandId(String id);

    List<EntityModel<ItemRest>> getAllItemsByBrandName(String name);

    List<EntityModel<ManufacturerRest>> getAllManufacturersByBrandsName(String name);

    List<EntityModel<BrandRest>> getAllBrandsByAddressCity(String city);

    List<EntityModel<BrandRest>> getAllBrandsByAddressCountry(String country);
}
