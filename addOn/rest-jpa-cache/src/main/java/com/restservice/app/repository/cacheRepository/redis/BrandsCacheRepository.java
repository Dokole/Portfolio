package com.restservice.app.repository.cacheRepository.redis;

import com.restservice.app.domain.cache.redis.containers.BrandsCache;
import org.springframework.data.repository.CrudRepository;

public interface BrandsCacheRepository extends CrudRepository<BrandsCache, String> {
}
