package com.example.smallTools.redis.redistemplate;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Description: redis hash缓存
 *               不同的是多了一层key的概念，用于分类key
 * @Author: woodyiiiiiii
 * @Date: 2022/1/8 20:52
 */
public class RedisHashCache {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private static final int CACHE_EXPIRE_TIME = 60 * 20 * 1000;

    /**
     * 是否存在
     */
    public boolean hasKey(String key) {
        Boolean aBoolean = redisTemplate.hasKey(key);
        return BooleanUtils.toBoolean(aBoolean);
    }

    /**
     * 存储
     */
    public void put(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
        redisTemplate.expire(key, CACHE_EXPIRE_TIME, TimeUnit.MILLISECONDS);
    }

    /**
     * 存储
     */
    public void put(String key, Object hashKey, Object value, long expireTime) {
        redisTemplate.opsForHash().put(key, hashKey, value);
        redisTemplate.expire(key, expireTime, TimeUnit.MILLISECONDS);
    }

    /**
     * 获取
     */
    public Object get(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 删除
     */
    public void delete(String key, String hashKey) {
        redisTemplate.opsForHash().delete(key, hashKey);
    }

}
