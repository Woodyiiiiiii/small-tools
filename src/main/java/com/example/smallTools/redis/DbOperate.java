package com.example.smallTools.redis;

import com.example.smallTools.redis.cache.Cache;
import com.example.smallTools.redis.cache.CacheResponse;
import com.example.smallTools.redis.strategy.CacheKeyResolver;
import com.example.smallTools.redis.strategy.CacheProxy;
import lombok.Setter;

/**
 * @Author:woody
 * @Date: 2022/1/8 21:12
 */
public enum DbOperate implements CacheProxy {

    QUERY, INSERT, UPDATE, DELETE
    ;

    @Setter
    public static Cache cache;

    private CacheProxy cacheProxy;

    DbOperate(CacheProxy cacheProxy) {
        this.cacheProxy = cacheProxy;
    }

    DbOperate() {
        this.cacheProxy = CacheStrategyEnum.QUERY;
    }

    @Override
    public CacheResponse<Object, Exception> beginCache(CacheKeyResolver cacheKeyResolver) {
        return this.cacheProxy.beginCache(cacheKeyResolver);
    }

    @Override
    public boolean isProcess(CacheResponse<?, ? extends Exception> cacheResponse) {
        return this.cacheProxy.isProcess(cacheResponse);
    }


    @Override
    public CacheResponse<Void, Exception> endCache(CacheKeyResolver cacheKeyResolver, Object object) {
        return this.cacheProxy.endCache(cacheKeyResolver, object);
    }


    private enum CacheStrategyEnum implements CacheProxy {
        QUERY {

            @Override
            public CacheResponse<Object, Exception> beginCache(CacheKeyResolver cacheKeyResolver) {
                return cache.get(cacheKeyResolver.getKey());
            }

            @Override
            public boolean isProcess(CacheResponse<?, ? extends Exception> cacheResponse) {
                return cacheResponse.isSucceed() || cacheResponse.isHasKey();
            }

            @Override
            public CacheResponse<Void, Exception> endCache(CacheKeyResolver cacheKeyResolver, Object object) {
                return cache.set(cacheKeyResolver.getKey(), object);
            }

        },
        UPDATE {

            @Override
            public CacheResponse<Object, Exception> beginCache(CacheKeyResolver cacheKeyResolver) {
                return cache.get(cacheKeyResolver.getKey());
            }

            @Override
            public boolean isProcess(CacheResponse<?, ? extends Exception> cacheResponse) {
                return cacheResponse.isSucceed() || cacheResponse.isHasKey();
            }

            @Override
            public CacheResponse<Void, Exception> endCache(CacheKeyResolver cacheKeyResolver, Object object) {
                String key = cacheKeyResolver.getKey();
                CacheResponse<Void, Exception> response = cache.hasKey(key);
                if (response.isSucceed()) {
                    cache.del(key);
                }
                return cache.set(cacheKeyResolver.getKey(), object);
            }

        }
    }

}
