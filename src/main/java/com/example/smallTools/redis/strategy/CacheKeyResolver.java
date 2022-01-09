package com.example.smallTools.redis.strategy;

import lombok.Getter;

import java.lang.reflect.Method;

/**
 * @Author:woody
 * @Date: 2022/1/9 13:46
 */
public class CacheKeyResolver {

    private final Class<?> clazz;

    private final Method method;

    private final Object[] args;

    private final String keyPrefix;

    //------------------------
    private String key;

    public CacheKeyResolver(String keyPrefix, Class<?> clazz, Method method, Object[] args) {
        this.clazz = clazz;
        this.method = method;
        this.args = args;
        this.keyPrefix = keyPrefix;
    }

    public String getKey() {
        generateKey();
        return key;
    }

    private void generateKey() {
        if (key != null) {
            return;
        }
        synchronized (clazz) {
            if (key != null) {
                return;
            }
            key = keyPrefix + ":" + clazz.getName() + "#" + method.getName();
        }
    }

}
