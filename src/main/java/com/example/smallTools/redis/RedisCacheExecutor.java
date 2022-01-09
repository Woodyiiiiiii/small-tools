package com.example.smallTools.redis;

import com.example.smallTools.redis.cache.CacheResponse;
import com.example.smallTools.redis.strategy.CacheKeyResolver;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author:woody
 * @Date: 2022/1/9 13:50
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class RedisCacheExecutor {

    @Around("@annotation(redisCache)")
    public Object execute(ProceedingJoinPoint proceedingJoinPoint, RedisCache redisCache) throws Throwable {
        CacheKeyResolver cacheKeyResolver = new CacheKeyResolver(redisCache.key(), proceedingJoinPoint.getTarget().getClass(),
                ((MethodSignature)(proceedingJoinPoint.getSignature())).getMethod(), proceedingJoinPoint.getArgs());
        CacheResponse<Object, Exception> beginResp = redisCache.dbOperate().beginCache(cacheKeyResolver);

        if (redisCache.dbOperate().isProcess(beginResp)) {
            return beginResp.getValue();
        }

        Object result = proceedingJoinPoint.proceed();
        CacheResponse<Void, Exception> endResp = redisCache.dbOperate().endCache(cacheKeyResolver, result);

        return result;
    }

}
