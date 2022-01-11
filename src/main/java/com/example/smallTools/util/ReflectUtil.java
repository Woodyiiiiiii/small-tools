package com.example.smallTools.util;

import org.aspectj.util.SoftHashMap;

/**
 * @Description: 反射工具类
 * @Author:woody
 * @Date: 2022/1/11 21:55
 */
public class ReflectUtil {

    // 软引用Map
    private final SoftHashMap<String, Class<?>> softHashMap = new SoftHashMap<>();

    /**
     * 基本类型和包装类是否是原始类型
     */
    public Boolean isPrimitive(Class<?> clazz) throws NoSuchFieldException {
        if (clazz.isPrimitive()) {
            return true;
        }
        return clazz.getDeclaredField("TYPE").getClass().isPrimitive();
    }

}
