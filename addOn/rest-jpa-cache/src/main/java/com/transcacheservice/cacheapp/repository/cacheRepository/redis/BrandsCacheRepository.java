package com.transcacheservice.cacheapp.repository.cacheRepository.redis;

import com.transcacheservice.cacheapp.domain.cache.redis.containers.BrandsCache;
import org.springframework.data.repository.CrudRepository;

public interface BrandsCacheRepository extends CrudRepository<BrandsCache, String> {
}
