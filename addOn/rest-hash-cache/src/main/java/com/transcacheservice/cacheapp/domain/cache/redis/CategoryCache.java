package com.transcacheservice.cacheapp.domain.cache.redis;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public class CategoryCache extends AbstractCacheObject {
    protected List<ItemCache> items = new ArrayList<>();

    public List<ItemCache> getItems() {
        return items;
    }

    public void setItems(List<ItemCache> items) {
        this.items = items;
    }
}
