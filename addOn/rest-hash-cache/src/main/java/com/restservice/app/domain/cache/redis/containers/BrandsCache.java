package com.restservice.app.domain.cache.redis.containers;

import com.restservice.app.domain.cache.redis.BrandCache;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@RedisHash(timeToLive = 60)
public class BrandsCache {

    @Id
    protected String id;

    protected List<BrandCache> brands = new ArrayList<>();

    public List<BrandCache> getBrands() {
        return brands;
    }

    public void setBrands(List<BrandCache> brands) {
        this.brands = brands;
    }
}
