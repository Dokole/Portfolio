package com.transcacheservice.cacheapp.domain.cache.redis.containers;

import com.transcacheservice.cacheapp.domain.cache.redis.ManufacturerCache;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@RedisHash(timeToLive = 60)
public class ManufacturersCache {

    @Id
    protected String id;

    protected List<ManufacturerCache> manufacturers = new ArrayList<>();

    public List<ManufacturerCache> getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(List<ManufacturerCache> manufacturers) {
        this.manufacturers = manufacturers;
    }


}
