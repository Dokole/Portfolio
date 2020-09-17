package com.restservice.app.service.cacheService;


import com.restservice.app.domain.cache.redis.containers.ManufacturersCache;

public interface ManufacturerCacheService {

    ManufacturersCache findCached(String id);

    void save(ManufacturersCache manufacturerCache);

    void deleteById(String id);

    void deleteAll();

    void invalidateRelatedCaches();

    void invalidateRelatedCachesDelay();

}
