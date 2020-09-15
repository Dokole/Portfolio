package com.transcacheservice.cacheapp.domain.cache.redis;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public abstract class AbstractCacheObject {

    protected Long id;
    protected Long version;
    protected String lastModifiedBy;
    protected String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
