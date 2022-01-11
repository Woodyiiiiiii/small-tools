package com.example.smallTools.redis.redistemplate;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description: redis缓存
 * @Author: woodyiiiiiii
 * @Date: 2022/1/8 18:24
 */
@Component
public class RedisMapCache {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private static final String CACHE_PREFIX = "cache:";

    /**
     * 缓存的超时时间，默认为20min
     */
    private static final int CACHE_EXPIRE = 20 * 60 * 1000;

    private String key(String cacheKey) {
        return CACHE_PREFIX + cacheKey;
    }

    /**
     * 是否有缓存
     */
    public Boolean hasKey(String cacheKey) {
        Boolean aBoolean = redisTemplate.hasKey(key(cacheKey));
        return BooleanUtils.toBoolean(aBoolean);
    }

    /**
     * 设置缓存
     */
    public void setValue(String cacheKey, Object value) {
        redisTemplate.opsForValue().set(key(cacheKey), value, CACHE_EXPIRE, TimeUnit.MILLISECONDS);
    }

    public void setValue(String cacheKey, Object value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key(cacheKey), value, timeout, timeUnit);
    }

    /**
     * 获取缓存
     */
    public Object getValue(String cacheKey) {
        return redisTemplate.opsForValue().get(key(cacheKey));
    }

    /**
     * 删除缓存
     */
    public void delete(String cacheKey) {
        redisTemplate.delete(key(cacheKey));
    }

    /**
     * 分布式锁设置
     */
    public Boolean setRedisLock(String cacheKey, Object value, long timeout, TimeUnit timeUnit) {
        return redisTemplate.opsForValue().setIfAbsent(cacheKey, value, timeout, timeUnit);
    }

    /**
     * 分布式锁获取
     */
    public Object getRedisLock(String cacheKey) {
        return redisTemplate.opsForValue().get(cacheKey);
    }

    /**
     * 通过lua脚本获取锁
     */
    public Object getRedisLockByLua(RedisScript<?> redisScript, List<String> cacheKeyList, Object value) {
        return redisTemplate.execute(redisScript, cacheKeyList, value);
    }

}
