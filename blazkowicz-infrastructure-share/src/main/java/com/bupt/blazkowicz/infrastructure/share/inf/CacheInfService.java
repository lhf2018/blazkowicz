package com.bupt.blazkowicz.infrastructure.share.inf;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * @author lhf2018
 */
@Component
public class CacheInfService {

    private static ValueOperations<String, String> VALUE_OPERATIONS;
    private static RedisTemplate<String, String> REDIS_TEMPLATE;

    @Autowired
    public void init(RedisTemplate<String, String> redisTemplate) {
        REDIS_TEMPLATE = redisTemplate;
        VALUE_OPERATIONS = redisTemplate.opsForValue();
    }

    public String get(String key) {
        return VALUE_OPERATIONS.get(key);
    }

    public void put(String key, String value, int expiredSeconds) {
        VALUE_OPERATIONS.set(key, value, expiredSeconds, TimeUnit.SECONDS);
    }

    public void delete(String key) {
        REDIS_TEMPLATE.delete(key);
    }
}
