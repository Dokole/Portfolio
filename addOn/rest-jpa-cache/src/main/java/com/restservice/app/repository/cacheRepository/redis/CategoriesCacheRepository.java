package com.restservice.app.repository.cacheRepository.redis;

import com.restservice.app.domain.cache.redis.containers.CategoriesCache;
import org.springframework.data.repository.CrudRepository;

public interface CategoriesCacheRepository extends CrudRepository<CategoriesCache, String> {
}
