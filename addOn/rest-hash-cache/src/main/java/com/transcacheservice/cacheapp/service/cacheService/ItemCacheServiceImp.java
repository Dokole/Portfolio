package com.transcacheservice.cacheapp.service.cacheService;

import com.transcacheservice.cacheapp.domain.cache.redis.BrandCache;
import com.transcacheservice.cacheapp.domain.cache.redis.CategoryCache;
import com.transcacheservice.cacheapp.domain.cache.redis.ItemCache;
import com.transcacheservice.cacheapp.domain.cache.redis.ManufacturerCache;
import com.transcacheservice.cacheapp.domain.cache.redis.containers.ItemsCache;
import com.transcacheservice.cacheapp.repository.cacheRepository.redis.RedisCacheRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;


@Service
public class ItemCacheServiceImp implements ItemCacheService {

    private final String itemsCollectionName = ItemCache.class.getName();

    private Logger logger = LoggerFactory.getLogger(ItemCacheServiceImp.class);

    private final RedisCacheRepository redisCacheRepository;
    private final TaskScheduler taskScheduler;

    @Autowired
    public ItemCacheServiceImp(RedisCacheRepository redisCacheRepository, TaskScheduler taskScheduler) {
        this.redisCacheRepository = redisCacheRepository;
        this.taskScheduler = taskScheduler;
    }

    @Override
    public ItemsCache findCached(String id) {
        return (ItemsCache) redisCacheRepository.findById(itemsCollectionName, id);
    }

    @Override
    public void save(String id, ItemsCache itemCache) {
        redisCacheRepository.save(itemsCollectionName, id, itemCache);
    }

    @Override
    public void deleteById(String id) {
        redisCacheRepository.deleteById(itemsCollectionName, id);
    }

    @Override
    public void deleteAll() {
        redisCacheRepository.deleteAll(itemsCollectionName);
    }

    @Override
    public void invalidateRelatedCaches() {
        deleteAll();
        redisCacheRepository.deleteAll(CategoryCache.class.getName());
        redisCacheRepository.deleteAll(BrandCache.class.getName());
        redisCacheRepository.deleteAll(ManufacturerCache.class.getName());
    }

    public void invalidateRelatedCachesDelay() {
        taskScheduler.schedule(this::invalidateRelatedCaches, Date.from(Instant.now().plusMillis(500)));
    }
}
