package com.restservice.app.service.cacheService;

import com.restservice.app.repository.cacheRepository.redis.CategoriesCacheRepository;
import com.restservice.app.repository.cacheRepository.redis.ItemsCacheRepository;
import com.restservice.app.domain.cache.redis.containers.CategoriesCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class CategoryCacheServiceImp implements CategoryCacheService {

    private final CategoriesCacheRepository categoriesCacheRepository;
    private final ItemsCacheRepository itemsCacheRepository;
    private final TaskScheduler taskScheduler;

    @Autowired
    public CategoryCacheServiceImp(CategoriesCacheRepository categoriesCacheRepository, ItemsCacheRepository itemsCacheRepository, TaskScheduler taskScheduler) {
        this.categoriesCacheRepository = categoriesCacheRepository;
        this.itemsCacheRepository = itemsCacheRepository;
        this.taskScheduler = taskScheduler;
    }


    @Override
    public CategoriesCache findCached(String id) {
        return categoriesCacheRepository.findById(id).orElse(null);
    }


    @Override
    public void save(CategoriesCache categoryCache) {
        categoriesCacheRepository.save(categoryCache);
    }

    @Override
    public void deleteById(String id) {
        categoriesCacheRepository.deleteById(id);
    }


    @Override
    public void deleteAll() {
        categoriesCacheRepository.deleteAll();
    }

    @Override
    public void invalidateRelatedCaches() {
        itemsCacheRepository.deleteAll();
        deleteAll();
    }

    public void invalidateRelatedCachesDelay() {
        taskScheduler.schedule(this::invalidateRelatedCaches, Date.from(Instant.now().plusMillis(500)));

    }
}
