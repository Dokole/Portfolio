package com.restservice.app.service.cacheService;

import com.restservice.app.repository.cacheRepository.redis.RedisCacheRepository;
import com.restservice.app.domain.cache.redis.BrandCache;
import com.restservice.app.domain.cache.redis.ItemCache;
import com.restservice.app.domain.cache.redis.ManufacturerCache;
import com.restservice.app.domain.cache.redis.containers.BrandsCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;

@Service
public class BrandCacheServiceImp implements BrandCacheService {

    private final String brandCollectionName = BrandCache.class.getName();
    private final RedisCacheRepository redisCacheRepository;
    private final TaskScheduler taskScheduler;

    @Autowired
    public BrandCacheServiceImp(RedisCacheRepository redisCacheRepository, TaskScheduler taskScheduler) {
        this.redisCacheRepository = redisCacheRepository;
        this.taskScheduler = taskScheduler;
    }

    @Override
    public BrandsCache findCached(String id) {
        return (BrandsCache) redisCacheRepository.findById(brandCollectionName, id);
    }


    @Override
    public void save(String id, BrandsCache brand) {
        redisCacheRepository.save(brandCollectionName, id, brand);
    }

    @Override
    public void deleteById(String id) {
        redisCacheRepository.deleteById(brandCollectionName, id);
    }


    @Override
    public void deleteAll() {
        redisCacheRepository.deleteAll(brandCollectionName);
    }

    @Override
    public void invalidateRelatedCaches() {
        deleteAll();
        redisCacheRepository.deleteAll(ItemCache.class.getName());
        redisCacheRepository.deleteAll(ManufacturerCache.class.getName());
    }

    public void invalidateRelatedCachesDelay() {
        taskScheduler.schedule(this::invalidateRelatedCaches, Date.from(Instant.now().plusMillis(500)));
    }

}
