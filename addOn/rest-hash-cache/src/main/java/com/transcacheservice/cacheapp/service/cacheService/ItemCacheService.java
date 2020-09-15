package com.transcacheservice.cacheapp.service.cacheService;

import com.transcacheservice.cacheapp.domain.cache.redis.containers.ItemsCache;


public interface ItemCacheService {

    ItemsCache findCached(String id);

    void save(String id, ItemsCache itemCache);

    void deleteById(String id);

    void deleteAll();

    void invalidateRelatedCaches();

    void invalidateRelatedCachesDelay();


}
