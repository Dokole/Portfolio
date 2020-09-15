package com.transcacheservice.cacheapp.domain.cache.redis.containers;

import com.transcacheservice.cacheapp.domain.cache.redis.ItemCache;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@RedisHash(timeToLive = 60)
public class ItemsCache {

    @Id
    protected String id;

    protected List<ItemCache> items = new ArrayList<>();

    public List<ItemCache> getItems() {
        return items;
    }

    public void setItems(List<ItemCache> items) {
        this.items = items;
    }
}
