package com.example.smallTools.util;

/**
 * @Description: 反射工具类
 * @Author:woody
 * @Date: 2022/1/11 21:55
 */
public class ReflectUtil {

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
