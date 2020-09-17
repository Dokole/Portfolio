package com.restservice.app.service.cacheService;

import com.restservice.app.repository.cacheRepository.redis.BrandsCacheRepository;
import com.restservice.app.repository.cacheRepository.redis.ItemsCacheRepository;
import com.restservice.app.repository.cacheRepository.redis.ManufacturersCacheRepository;
import com.restservice.app.domain.cache.redis.containers.BrandsCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;

@Service
public class BrandCacheServiceImp implements BrandCacheService {

    private final ManufacturersCacheRepository manufacturersCacheRepository;
    private final ItemsCacheRepository itemsCacheRepository;
    private final BrandsCacheRepository brandsCacheRepository;
    private final TaskScheduler taskScheduler;

    @Autowired
    public BrandCacheServiceImp(ManufacturersCacheRepository manufacturersCacheRepository, ItemsCacheRepository itemsCacheRepository, BrandsCacheRepository brandsCacheRepository, TaskScheduler taskScheduler) {
        this.manufacturersCacheRepository = manufacturersCacheRepository;
        this.itemsCacheRepository = itemsCacheRepository;
        this.brandsCacheRepository = brandsCacheRepository;
        this.taskScheduler = taskScheduler;
    }

    @Override
    public BrandsCache findCached(String id) {
        return brandsCacheRepository.findById(id).orElse(null);
    }


    @Override
    public void save(BrandsCache brand) {
        brandsCacheRepository.save(brand);
    }

    @Override
    public void deleteById(String id) {
        brandsCacheRepository.deleteById(id);
    }


    @Override
    public void deleteAll() {
        brandsCacheRepository.deleteAll();
    }

    @Override
    public void invalidateRelatedCaches() {
        deleteAll();
        itemsCacheRepository.deleteAll();
        manufacturersCacheRepository.deleteAll();
    }

    public void invalidateRelatedCachesDelay() {
        taskScheduler.schedule(this::invalidateRelatedCaches, Date.from(Instant.now().plusMillis(500)));
    }

}
