package com.example.smallTools.redis.cache;

import com.example.smallTools.redis.DbOperate;
import com.example.smallTools.redis.redistemplate.RedisMapCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author:woody
 * @Date: 2022/1/8 21:11
 */
@Component
@Slf4j
public class BaseCache implements Cache, InitializingBean {

    @Resource
    private RedisMapCache redisMapCache;

    // 初始化后放置到DbOperate中
    @Override
    public void afterPropertiesSet() throws Exception {
        DbOperate.setCache(this);
    }

    @Override
    public CacheResponse<Void, Exception> hasKey(String key) {
        try {
            Boolean hasKey = redisMapCache.hasKey(key);
            return CacheResponse.success(hasKey, null);
        } catch (Exception e) {
            log.error("redis hasKey error", e);
            return CacheResponse.fail(e);
        }
    }

    @Override
    public CacheResponse<Object, Exception> get(String key) {
        try {
            Boolean hasKey = redisMapCache.hasKey(key);
            Object value = null;
            if (hasKey) {
                value = redisMapCache.getValue(key);
            }
            return CacheResponse.success(hasKey, value);
        } catch (Exception e) {
            log.error("redis get error", e);
            return CacheResponse.fail(e);
        }
    }

    @Override
    public CacheResponse<Void, Exception> set(String key, Object value) {
        try {
            redisMapCache.setValue(key, value);
            return CacheResponse.success(true, null);
        } catch (Exception e) {
            log.error("redis set error", e);
            return CacheResponse.fail(e);
        }
    }

    @Override
    public CacheResponse<Void, Exception> del(String key) {
        try {
            Boolean hasKey = redisMapCache.hasKey(key);
            if (hasKey) {
                redisMapCache.delete(key);
            }
            return CacheResponse.success(hasKey, null);
        } catch (Exception e) {
            log.error("redis del error", e);
            return CacheResponse.fail(e);
        }
    }

}
