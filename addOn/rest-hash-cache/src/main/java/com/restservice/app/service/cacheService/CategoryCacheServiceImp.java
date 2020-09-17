package com.restservice.app.service.cacheService;

import com.restservice.app.repository.cacheRepository.redis.RedisCacheRepository;
import com.restservice.app.domain.cache.redis.CategoryCache;
import com.restservice.app.domain.cache.redis.ItemCache;
import com.restservice.app.domain.cache.redis.containers.CategoriesCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class CategoryCacheServiceImp implements CategoryCacheService {

    private final String categoryCollectionName = CategoryCache.class.getName();

    private final RedisCacheRepository redisCacheRepository;
    private final TaskScheduler taskScheduler;

    @Autowired
    public CategoryCacheServiceImp(RedisCacheRepository redisCacheRepository, TaskScheduler taskScheduler) {
        this.redisCacheRepository = redisCacheRepository;
        this.taskScheduler = taskScheduler;
    }


    @Override
    public CategoriesCache findCached(String id) {
        return (CategoriesCache) redisCacheRepository.findById(categoryCollectionName, id);
    }


    @Override
    public void save(String id, CategoriesCache categoryCache) {
        redisCacheRepository.save(categoryCollectionName, id, categoryCache);
    }

    @Override
    public void deleteById(String id) {
        redisCacheRepository.deleteById(categoryCollectionName, id);
    }


    @Override
    public void deleteAll() {
        redisCacheRepository.deleteAll(categoryCollectionName);
    }

    @Override
    public void invalidateRelatedCaches() {
        redisCacheRepository.deleteAll(ItemCache.class.getName());
        deleteAll();
    }

    public void invalidateRelatedCachesDelay() {
        taskScheduler.schedule(this::invalidateRelatedCaches, Date.from(Instant.now().plusMillis(500)));

    }
}
