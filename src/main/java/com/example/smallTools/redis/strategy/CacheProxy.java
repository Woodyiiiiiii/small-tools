package com.example.smallTools.redis.strategy;

import com.example.smallTools.redis.cache.CacheResponse;

/**
 * @Author:woody
 * @Date: 2022/1/9 13:45
 */
public interface CacheProxy {

    CacheResponse<Object, Exception> beginCache(CacheKeyResolver cacheKeyResolver);

    boolean isProcess(CacheResponse<?, ? extends Exception> cacheResponse);

    CacheResponse<Void, Exception> endCache(CacheKeyResolver cacheKeyResolver, Object object);

}
