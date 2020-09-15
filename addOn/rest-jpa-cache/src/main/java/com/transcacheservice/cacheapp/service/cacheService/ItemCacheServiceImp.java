package com.transcacheservice.cacheapp.service.cacheService;

import com.transcacheservice.cacheapp.domain.cache.redis.containers.ItemsCache;
import com.transcacheservice.cacheapp.repository.cacheRepository.redis.BrandsCacheRepository;
import com.transcacheservice.cacheapp.repository.cacheRepository.redis.CategoriesCacheRepository;
import com.transcacheservice.cacheapp.repository.cacheRepository.redis.ItemsCacheRepository;
import com.transcacheservice.cacheapp.repository.cacheRepository.redis.ManufacturersCacheRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;


@Service
public class ItemCacheServiceImp implements ItemCacheService {

    private Logger logger = LoggerFactory.getLogger(ItemCacheServiceImp.class);

    private final ManufacturersCacheRepository manufacturersCacheRepository;
    private final ItemsCacheRepository itemsCacheRepository;
    private final BrandsCacheRepository brandsCacheRepository;
    private final CategoriesCacheRepository categoriesCacheRepository;
    private final TaskScheduler taskScheduler;

    @Autowired
    public ItemCacheServiceImp(ManufacturersCacheRepository manufacturersCacheRepository, ItemsCacheRepository itemsCacheRepository, BrandsCacheRepository brandsCacheRepository, CategoriesCacheRepository categoriesCacheRepository, TaskScheduler taskScheduler) {
        this.manufacturersCacheRepository = manufacturersCacheRepository;
        this.itemsCacheRepository = itemsCacheRepository;
        this.brandsCacheRepository = brandsCacheRepository;
        this.categoriesCacheRepository = categoriesCacheRepository;
        this.taskScheduler = taskScheduler;
    }

    @Override
    public ItemsCache findCached(String id) {
        return itemsCacheRepository.findById(id).orElse(null);
    }

    @Override
    public void save(ItemsCache itemCache) {
        itemsCacheRepository.save(itemCache);
    }

    @Override
    public void deleteById(String id) {
        itemsCacheRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        itemsCacheRepository.deleteAll();
    }

    @Override
    public void invalidateRelatedCaches() {
        deleteAll();
        categoriesCacheRepository.deleteAll();
        brandsCacheRepository.deleteAll();
        manufacturersCacheRepository.deleteAll();
    }

    public void invalidateRelatedCachesDelay() {
        taskScheduler.schedule(this::invalidateRelatedCaches, Date.from(Instant.now().plusMillis(500)));
    }
}
