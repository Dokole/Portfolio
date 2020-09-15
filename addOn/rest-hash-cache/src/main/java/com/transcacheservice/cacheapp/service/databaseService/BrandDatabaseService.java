package com.transcacheservice.cacheapp.service.databaseService;

import com.transcacheservice.cacheapp.domain.cache.redis.BrandCache;

import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public interface BrandDatabaseService {

    BrandCache getBrandById(String id);

    BrandCache getBrandByName(String name);

    BrandCache getBrandByItemId(String id);

    BrandCache getBrandByItemName(String name);

    List<BrandCache> getAllBrandsByAddressCity(String city);

    List<BrandCache> getAllBrandsByAddressCountry(String country);

    List<BrandCache> getAllBrandsByManufacturersId(String id);

    List<BrandCache> getAllBrandsByManufacturersName(String name);

    BrandCache createBrand(BrandCache brand);

    BrandCache updateBrand(BrandCache brand);

    boolean deleteBrandById(String id);

    List<BrandCache> getAllBrands();

}
