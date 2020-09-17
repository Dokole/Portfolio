package com.restservice.app.service.cacheService;


import com.restservice.app.domain.cache.redis.containers.CategoriesCache;


public interface CategoryCacheService {

    CategoriesCache findCached(String id);

    void save(String id, CategoriesCache categoryCache);

    void deleteById(String id);

    void deleteAll();

    void invalidateRelatedCaches();

    void invalidateRelatedCachesDelay();
}
