package com.transcacheservice.cacheapp.repository.cacheRepository.redis;

import com.transcacheservice.cacheapp.domain.cache.redis.containers.ManufacturersCache;
import org.springframework.data.repository.CrudRepository;

public interface ManufacturersCacheRepository extends CrudRepository<ManufacturersCache, String> {
}
