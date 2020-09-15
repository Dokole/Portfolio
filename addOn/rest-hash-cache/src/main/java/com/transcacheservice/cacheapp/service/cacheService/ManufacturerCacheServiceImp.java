package com.transcacheservice.cacheapp.service.cacheService;

import com.transcacheservice.cacheapp.domain.cache.redis.BrandCache;
import com.transcacheservice.cacheapp.domain.cache.redis.ItemCache;
import com.transcacheservice.cacheapp.domain.cache.redis.ManufacturerCache;
import com.transcacheservice.cacheapp.domain.cache.redis.containers.ManufacturersCache;
import com.transcacheservice.cacheapp.repository.cacheRepository.redis.RedisCacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;


@Service
public class ManufacturerCacheServiceImp implements ManufacturerCacheService {

    private static final String manufacturersCollectionName = ManufacturerCache.class.getName();

    private final RedisCacheRepository redisCacheRepository;
    private final TaskScheduler taskScheduler;

    @Autowired
    public ManufacturerCacheServiceImp(RedisCacheRepository redisCacheRepository, TaskScheduler taskScheduler) {
        this.redisCacheRepository = redisCacheRepository;
        this.taskScheduler = taskScheduler;
    }

    @Override
    public ManufacturersCache findCached(String id) {
        return (ManufacturersCache) redisCacheRepository.findById(manufacturersCollectionName, id);
    }

    @Override
    public void save(String id, ManufacturersCache manufacturerCache) {
        redisCacheRepository.save(manufacturersCollectionName, id, manufacturerCache);
    }

    @Override
    public void deleteById(String id) {
        redisCacheRepository.deleteById(manufacturersCollectionName, id);
    }

    @Override
    public void deleteAll() {
        redisCacheRepository.deleteAll(manufacturersCollectionName);
    }

    @Override
    public void invalidateRelatedCaches() {
        deleteAll();
        redisCacheRepository.deleteAll(BrandCache.class.getName());
        redisCacheRepository.deleteAll(ItemCache.class.getName());
    }

    public void invalidateRelatedCachesDelay() {
        taskScheduler.schedule(this::invalidateRelatedCaches, Date.from(Instant.now().plusMillis(500)));
    }
}
