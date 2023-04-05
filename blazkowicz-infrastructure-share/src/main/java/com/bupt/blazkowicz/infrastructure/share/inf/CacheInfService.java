package com.bupt.blazkowicz.infrastructure.share.inf;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * @author lhf2018
 * @date 2022/10/29 15:55
 */
@Component
public class CacheInfService {

    private static ValueOperations<String, String> VALUE_OPERATIONS;

    @Autowired
    public void init(RedisTemplate<String, String> redisTemplate) {
        VALUE_OPERATIONS = redisTemplate.opsForValue();
    }

    /**
     * 获取数据库中的值
     *
     * @param key
     * @return
     */
    public String get(String key) {
        return VALUE_OPERATIONS.get(key);
    }

    public void put(String key, String value, int expiredSeconds) {
        VALUE_OPERATIONS.set(key, value, expiredSeconds, TimeUnit.SECONDS);
    }
}
