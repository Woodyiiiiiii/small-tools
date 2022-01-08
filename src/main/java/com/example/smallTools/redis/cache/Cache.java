package com.example.smallTools.redis.cache;

/**
 * @Author:woody
 * @Date: 2022/1/8 21:10
 */
public interface Cache {

    /**
     * 是否有缓存
     */
    CacheResponse<Void, Exception> hasKey(String key);

    /**
     * 获取缓存
     */
    CacheResponse<Object, Exception> get(String key);

    /**
     * 设置缓存
     */
    CacheResponse<Void, Exception> set(String key, Object value);

    /**
     * 删除缓存
     */
    CacheResponse<Void, Exception> del(String key);

}
