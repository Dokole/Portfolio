package com.transcacheservice.cacheapp.service.databaseService;

import com.transcacheservice.cacheapp.domain.cache.redis.containers.BrandsCache;
import com.transcacheservice.cacheapp.exceptions.BadRequestException;
import com.transcacheservice.cacheapp.exceptions.NotFoundException;
import com.transcacheservice.cacheapp.domain.cache.redis.BrandCache;
import com.transcacheservice.cacheapp.service.cacheService.BrandCacheService;
import com.transcacheservice.cacheapp.service.soapService.BrandSoapService;
import com.transcacheservice.cacheapp.util.CacheIdNamer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Service
public class BrandDatabaseServiceImp implements BrandDatabaseService {

    private final Logger logger = LoggerFactory.getLogger(BrandDatabaseServiceImp.class);

    private final BrandCacheService brandCacheService;
    private final BrandSoapService brandSoapService;
    private final CacheIdNamer cacheIdNamer;

    @Autowired
    public BrandDatabaseServiceImp(BrandCacheService brandCacheService, BrandSoapService brandSoapService, CacheIdNamer cacheIdNamer) {
        this.brandCacheService = brandCacheService;
        this.brandSoapService = brandSoapService;

        this.cacheIdNamer = cacheIdNamer;
    }

    @Override
    public BrandCache getBrandById(String id) {
        try {
            BrandsCache brands = brandCacheService.findCached(cacheIdNamer.getNameOfExecutableMethod() + id);
            if (brands == null) {
                brands = new BrandsCache();
                brands.getBrands().add(brandSoapService.getBrandById(Long.parseLong(id)));
                brandCacheService.save(cacheIdNamer.getNameOfExecutableMethod()+id, brands);
            }
            return brands.getBrands().get(0);
        } catch (NumberFormatException e) {
            logger.error("Illegal URI argument = " + id + " Message: " + e.getLocalizedMessage());
            throw new BadRequestException("Illegal URI argument = " + id);
        }
    }

    @Override
    public BrandCache getBrandByName(String name) {
        BrandsCache brands = brandCacheService.findCached(cacheIdNamer.getNameOfExecutableMethod() + name);
        if (brands == null) {
            brands = new BrandsCache();
            brands.getBrands().add(brandSoapService.getBrandByName(name));
            brandCacheService.save(cacheIdNamer.getNameOfExecutableMethod() + name, brands);
        }
        return brands.getBrands().get(0);
    }

    @Override
    public BrandCache getBrandByItemId(String id) {
        try {
            BrandsCache brands = brandCacheService.findCached(cacheIdNamer.getNameOfExecutableMethod() + id);
            if (brands == null) {
                brands = new BrandsCache();
                brands.getBrands().add(brandSoapService.getBrandByItemId(Long.parseLong(id)));
                brandCacheService.save(cacheIdNamer.getNameOfExecutableMethod()+id, brands);
            }
            return brands.getBrands().get(0);
        } catch (NumberFormatException e) {
            logger.error("Illegal URI argument = " + id + " Message: " + e.getLocalizedMessage());
            throw new BadRequestException("Illegal URI argument = " + id);
        }
    }

    @Override
    public BrandCache getBrandByItemName(String name) {
        BrandsCache brands = brandCacheService.findCached(cacheIdNamer.getNameOfExecutableMethod() + name);
        if (brands == null) {
            brands = new BrandsCache();
            brands.getBrands().add(brandSoapService.getBrandByItemName(name));
            brandCacheService.save(cacheIdNamer.getNameOfExecutableMethod() + name, brands);
        }
        return brands.getBrands().get(0);
    }

    @Override
    public BrandCache createBrand(BrandCache brand) {

        brandCacheService.invalidateRelatedCaches();
        BrandCache createdBrand = brandSoapService.createBrand(brand);
        brandCacheService.invalidateRelatedCachesDelay();

        return createdBrand;
    }

    @Override
    public BrandCache updateBrand(BrandCache brand) {

        brandCacheService.invalidateRelatedCaches();
        BrandCache updatedBrand = brandSoapService.updateBrand(brand);
        brandCacheService.invalidateRelatedCachesDelay();

        return updatedBrand;
    }

    @Override
    public boolean deleteBrandById(String id) {
        brandCacheService.invalidateRelatedCaches();
        try {
            if (brandSoapService.deleteBrandById(Long.parseLong(id))) {
                brandCacheService.invalidateRelatedCachesDelay();
                return true;
            } else return false;
        } catch (NumberFormatException e) {
            logger.error("Illegal URI argument = " + id + " Message: " + e.getLocalizedMessage());
            throw new BadRequestException("Illegal URI argument = " + id);
        }
    }

    //Uncached
    @Override
    public List<BrandCache> getAllBrands() {
        List<BrandCache> results = brandSoapService.getAllBrands();
        if(results.isEmpty()) {
            throw new NotFoundException("No brands found");
        }
        return results;
    }


    @Override
    public List<BrandCache> getAllBrandsByAddressCity(String city) {
        BrandsCache brandsCache = brandCacheService.findCached(cacheIdNamer.getNameOfExecutableMethod() + city);
        if(brandsCache == null) {
            brandsCache = new BrandsCache();
            brandsCache.setBrands(brandSoapService.getBrandsByAddressCity(city));
            brandCacheService.save(cacheIdNamer.getNameOfExecutableMethod() + city, brandsCache);
        }
        return brandsCache.getBrands();
    }

    @Override
    public List<BrandCache> getAllBrandsByAddressCountry(String country) {
        BrandsCache brandsCache = brandCacheService.findCached(cacheIdNamer.getNameOfExecutableMethod() + country);
        if(brandsCache == null) {
            brandsCache = new BrandsCache();
            brandsCache.setBrands(brandSoapService.getBrandsByAddressCountry(country));
            brandCacheService.save(cacheIdNamer.getNameOfExecutableMethod() + country, brandsCache);
        }
        return brandsCache.getBrands();
    }

    @Override
    public List<BrandCache> getAllBrandsByManufacturersId(String id) {
        BrandsCache brandsCache = brandCacheService.findCached(cacheIdNamer.getNameOfExecutableMethod() + id);
        if(brandsCache == null) {
            brandsCache = new BrandsCache();
            brandsCache.setBrands(brandSoapService.getBrandsByManufacturersId(Long.parseLong(id)));
            brandCacheService.save(cacheIdNamer.getNameOfExecutableMethod() + id, brandsCache);
        }
        return brandsCache.getBrands();
    }

    @Override
    public List<BrandCache> getAllBrandsByManufacturersName(String name) {
        BrandsCache brandsCache = brandCacheService.findCached(cacheIdNamer.getNameOfExecutableMethod() + name);
        if(brandsCache == null) {
            brandsCache = new BrandsCache();
            brandsCache.setBrands(brandSoapService.getBrandsByManufacturersName(name));
            brandCacheService.save(cacheIdNamer.getNameOfExecutableMethod() + name, brandsCache);
        }
        return brandsCache.getBrands();
    }
}
