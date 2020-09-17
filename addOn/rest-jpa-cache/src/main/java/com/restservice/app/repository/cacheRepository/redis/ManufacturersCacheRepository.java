package com.restservice.app.repository.cacheRepository.redis;

import com.restservice.app.domain.cache.redis.containers.ManufacturersCache;
import org.springframework.data.repository.CrudRepository;

public interface ManufacturersCacheRepository extends CrudRepository<ManufacturersCache, String> {
}
