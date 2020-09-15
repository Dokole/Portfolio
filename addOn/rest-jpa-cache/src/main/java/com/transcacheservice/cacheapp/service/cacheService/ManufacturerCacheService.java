package com.transcacheservice.cacheapp.service.cacheService;


import com.transcacheservice.cacheapp.domain.cache.redis.containers.ManufacturersCache;

public interface ManufacturerCacheService {

    ManufacturersCache findCached(String id);

    void save(ManufacturersCache manufacturerCache);

    void deleteById(String id);

    void deleteAll();

    void invalidateRelatedCaches();

    void invalidateRelatedCachesDelay();

}
