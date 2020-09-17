package com.restservice.app.service.cacheService;

import com.restservice.app.repository.cacheRepository.redis.BrandsCacheRepository;
import com.restservice.app.repository.cacheRepository.redis.ItemsCacheRepository;
import com.restservice.app.repository.cacheRepository.redis.ManufacturersCacheRepository;
import com.restservice.app.domain.cache.redis.containers.ManufacturersCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;


@Service
public class ManufacturerCacheServiceImp implements ManufacturerCacheService {

    private final ManufacturersCacheRepository manufacturersCacheRepository;
    private final ItemsCacheRepository itemsCacheRepository;
    private final BrandsCacheRepository brandsCacheRepository;
    private final TaskScheduler taskScheduler;

    @Autowired
    public ManufacturerCacheServiceImp(ManufacturersCacheRepository manufacturersCacheRepository, ItemsCacheRepository itemsCacheRepository, BrandsCacheRepository brandsCacheRepository, TaskScheduler taskScheduler) {
        this.manufacturersCacheRepository = manufacturersCacheRepository;
        this.itemsCacheRepository = itemsCacheRepository;
        this.brandsCacheRepository = brandsCacheRepository;
        this.taskScheduler = taskScheduler;
    }

    @Override
    public ManufacturersCache findCached(String id) {
        return manufacturersCacheRepository.findById(id).orElse(null);
    }

    @Override
    public void save(ManufacturersCache manufacturerCache) {
        manufacturersCacheRepository.save(manufacturerCache);
    }

    @Override
    public void deleteById(String id) {
        manufacturersCacheRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        manufacturersCacheRepository.deleteAll();
    }

    @Override
    public void invalidateRelatedCaches() {
        deleteAll();
        itemsCacheRepository.deleteAll();
        brandsCacheRepository.deleteAll();
    }

    public void invalidateRelatedCachesDelay() {
        taskScheduler.schedule(this::invalidateRelatedCaches, Date.from(Instant.now().plusMillis(500)));
    }
}
