package com.restservice.app.service.cacheService;


import com.restservice.app.domain.cache.redis.containers.BrandsCache;


public interface BrandCacheService {

    BrandsCache findCached(String id);

    void save(BrandsCache brand);

    void deleteById(String id);

    void deleteAll();

    void invalidateRelatedCaches();

    void invalidateRelatedCachesDelay();
}
