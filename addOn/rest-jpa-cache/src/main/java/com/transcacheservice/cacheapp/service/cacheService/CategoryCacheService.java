package com.transcacheservice.cacheapp.service.cacheService;


import com.transcacheservice.cacheapp.domain.cache.redis.containers.CategoriesCache;


public interface CategoryCacheService {

    CategoriesCache findCached(String id);

    void save(CategoriesCache categoryCache);

    void deleteById(String id);

    void deleteAll();

    void invalidateRelatedCaches();

    void invalidateRelatedCachesDelay();
}
