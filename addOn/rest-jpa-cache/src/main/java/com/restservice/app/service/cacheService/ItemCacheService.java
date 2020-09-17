package com.restservice.app.service.cacheService;

import com.restservice.app.domain.cache.redis.containers.ItemsCache;


public interface ItemCacheService {

    ItemsCache findCached(String id);

    void save(ItemsCache itemCache);

    void deleteById(String id);

    void deleteAll();

    void invalidateRelatedCaches();

    void invalidateRelatedCachesDelay();


}
