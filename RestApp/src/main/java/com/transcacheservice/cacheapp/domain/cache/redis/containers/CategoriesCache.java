package com.transcacheservice.cacheapp.domain.cache.redis.containers;

import com.transcacheservice.cacheapp.domain.cache.redis.CategoryCache;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@RedisHash(timeToLive = 60)
public class CategoriesCache {

    @Id
    protected String id;

    protected List<CategoryCache> categories = new ArrayList<>();

    public List<CategoryCache> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryCache> categories) {
        this.categories = categories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
