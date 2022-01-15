package com.example.smallTools.redis.strategy;

import cn.hutool.core.util.StrUtil;
import com.example.smallTools.redis.RedisCacheKey;
import com.example.smallTools.util.ReflectUtil;
import lombok.Getter;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        // 加锁？
        synchronized (clazz) {
            if (key != null) {
                return;
            }
            String prefix = StrUtil.isEmpty(keyPrefix) ? clazz.getName() + "#" + method.getName() : keyPrefix;
            if (args != null && args.length > 0) {
                // 这里开始获取类的属性实际的值
                key += resolveRedisCacheKeys(objectMap());
            } else {
                key = prefix + "_null";
            }
        }
    }

    /**
     * 获取parameter对每个@RedisCacheKey注解的fieldNames的映射关系
     */
    private Map<Object, String[]> objectMap() {
        Map<Object, RedisCacheKey> objectAMap = ReflectUtil.getAnnotationOfMethod(method, RedisCacheKey.class);
        Map<Object, String[]> objectStringArrayMap = new HashMap<>(objectAMap.size());
        for (Map.Entry<Object, RedisCacheKey> entry : objectAMap.entrySet()) {
            objectStringArrayMap.put(entry.getKey(), entry.getValue().filedNames());
        }
        return objectStringArrayMap;
    }

    /**
     * 获取每个@RedisCacheKey注解的field实际的值，然后用_拼接
     */
    private String resolveRedisCacheKeys(Map<Object, String[]> objectStringArrayMap) {
        return objectStringArrayMap.entrySet().stream()
                .map(entry -> {
                    if (ReflectUtil.isPrimitive(entry.getKey().getClass())) {
                        return String.valueOf(entry.getKey());
                    } else {
                        List<Object> filedValues = Stream.of(entry.getValue())
                                .peek(fieldName -> {
                                    if (StrUtil.isEmpty(fieldName)) {
                                        throw new IllegalArgumentException("filedName不能为空");
                                    }
                                })
                                .map(filedName -> ReflectUtil.getFiledValues(entry.getKey(), filedName))
                                .collect(Collectors.toList());
                        return StrUtil.join("_", filedValues);
                    }
                })
                .collect(Collectors.joining());
    }

}
