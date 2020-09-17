package com.restservice.app.service.soapService;


import com.restservice.app.domain.cache.redis.BrandCache;

import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public interface BrandSoapService {

    BrandCache getBrandById(Long id);

    BrandCache getBrandByName(String name);

    BrandCache getBrandByItemId(Long id);

    BrandCache getBrandByItemName(String name);

    List<BrandCache> getAllBrands();

    BrandCache createBrand(BrandCache brand);

    BrandCache updateBrand(BrandCache brand);

    boolean deleteBrandById(Long id);

    List<BrandCache> getBrandsByManufacturersId(Long id);

    List<BrandCache>  getBrandsByManufacturersName(String name);

    List<BrandCache> getBrandsByAddressCountry(String country);

    List<BrandCache> getBrandsByAddressCity(String city);

}
