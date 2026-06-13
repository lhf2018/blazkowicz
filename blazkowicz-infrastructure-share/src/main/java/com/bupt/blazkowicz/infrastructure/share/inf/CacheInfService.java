package com.bupt.blazkowicz.infrastructure.share.inf;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * @author lhf2018
 */
@Component
public class CacheInfService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheInfService.class);

    private static ValueOperations<String, String> VALUE_OPERATIONS;
    private static RedisTemplate<String, String> REDIS_TEMPLATE;

    @Autowired
    public void init(RedisTemplate<String, String> redisTemplate) {
        REDIS_TEMPLATE = redisTemplate;
        VALUE_OPERATIONS = redisTemplate.opsForValue();
    }

    public String get(String key) {
        try {
            return VALUE_OPERATIONS.get(key);
        } catch (RuntimeException e) {
            logRedisFailure("get", key, e);
            return null;
        }
    }

    public void put(String key, String value, int expiredSeconds) {
        try {
            VALUE_OPERATIONS.set(key, value, expiredSeconds, TimeUnit.SECONDS);
        } catch (RuntimeException e) {
            logRedisFailure("put", key, e);
        }
    }

    public void delete(String key) {
        try {
            REDIS_TEMPLATE.delete(key);
        } catch (RuntimeException e) {
            logRedisFailure("delete", key, e);
        }
    }

    private void logRedisFailure(String operation, String key, RuntimeException e) {
        LOGGER.warn("Redis {} failed for key [{}], falling back without cache: {}", operation, key, e.getMessage());
    }
}
