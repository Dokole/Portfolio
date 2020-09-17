package com.restservice.app.repository.cacheRepository.redis;

import com.restservice.app.domain.cache.redis.containers.ItemsCache;
import org.springframework.data.repository.CrudRepository;

public interface ItemsCacheRepository extends CrudRepository<ItemsCache, String> {
}
