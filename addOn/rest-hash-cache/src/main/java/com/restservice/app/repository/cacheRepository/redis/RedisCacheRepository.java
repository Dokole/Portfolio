package com.restservice.app.repository.cacheRepository.redis;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public interface RedisCacheRepository {

    Object findById(String collection, String id);

    void save(String collection, String id, Object object);

    void deleteById(String collection, String id);

    void deleteAll(String collection);

}
