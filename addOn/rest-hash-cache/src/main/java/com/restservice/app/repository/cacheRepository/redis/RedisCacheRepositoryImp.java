package com.restservice.app.repository.cacheRepository.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 *
 * Custom implementation of Redis repository. Standard Jpa repositories hadn't needed
 * agile possibilities for key naming (The point is to store requests here, not unique objects)
 *
 * Hash = Dto object name
 * Hash key = Request name with parameters
 * Hash value = Json object
 */

@Repository
public class RedisCacheRepositoryImp implements RedisCacheRepository {

    private final Logger logger = LoggerFactory.getLogger(RedisCacheRepositoryImp.class);

    protected final RedisTemplate<String, Object> redisTemplate;

    protected HashOperations<String, String, Object> hashOperations;

    @Autowired
    public RedisCacheRepositoryImp(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void initOperations() {
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public Object findById(String collection, String id) {
        Object object = this.hashOperations.get(collection, id);
        return object;
    }

    @Override
    public void save(String collection, String id, Object object) {
        this.hashOperations.put(collection, id, object);
    }


    @Override
    public void deleteById(String collection, String id) {
        this.hashOperations.delete(collection, id);
    }

    @Override
    public void deleteAll(String collection) {
        redisTemplate.delete(collection);
    }

}
