package com.transcacheservice.cacheapp.repository.cacheRepository.redis;

import com.transcacheservice.cacheapp.domain.cache.redis.containers.CategoriesCache;
import org.springframework.data.repository.CrudRepository;

public interface CategoriesCacheRepository extends CrudRepository<CategoriesCache, String> {
}
