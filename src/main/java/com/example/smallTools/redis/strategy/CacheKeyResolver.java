package com.example.smallTools.redis.strategy;

import lombok.Getter;

import java.lang.reflect.Method;

/**
 * @Author:woody
 * @Date: 2022/1/9 13:46
 */
public class CacheKeyResolver {

    /**
     * 方法所在的类class
     */
    private final Class<?> clazz;

    /**
     * 方法
     */
    private final Method method;

    /**
     * 参数实例
     */
    private final Object[] args;

    /**
     * 注解自身的key前缀
     */
    private final String keyPrefix;

    // 目标生成的key
    // 默认是类名+方法名+参数
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

    /**
     * 生成key
     * 默认是类名+方法名+参数
     */
    private void generateKey() {
        if (key != null) {
            return;
        }
        synchronized (clazz) {
            if (key != null) {
                return;
            }
            key = clazz.getName() + "#" + method.getName();
            if (keyPrefix != null) {
                key = keyPrefix + ":" + key;
            }
            StringBuilder sb = new StringBuilder(key);
            if (args != null && args.length > 0) {
                for (Object arg : args) {
                    sb.append("_").append(arg);
                }
                key = sb.toString();
            } else {
                key = key + "_null";
            }
        }
    }

}
