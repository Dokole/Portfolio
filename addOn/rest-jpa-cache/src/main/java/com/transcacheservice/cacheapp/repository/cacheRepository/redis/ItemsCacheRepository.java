package com.transcacheservice.cacheapp.repository.cacheRepository.redis;

import com.transcacheservice.cacheapp.domain.cache.redis.containers.ItemsCache;
import org.springframework.data.repository.CrudRepository;

public interface ItemsCacheRepository extends CrudRepository<ItemsCache, String> {
}
