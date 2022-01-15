package com.example.smallTools.util;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.util.SoftHashMap;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.invoke.MethodHandle;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

/**
 * @Description: 反射工具类
 * @Author:woody
 * @Date: 2022/1/11 21:55
 */
@Slf4j
public class ReflectUtil {

    // 软引用Map
    private static final SoftHashMap<String, Object> SOFT_HASH_MAP = new SoftHashMap<>();

    private enum PrefixMethod {
        GET("get"),
        SET("set");
        ;

        @Getter
        private final String code;

        PrefixMethod(String code) {
            this.code = code;
        }
    }

    private static Object getCache(String key) {
        return SOFT_HASH_MAP.get(key);
    }

    private static void setCache(String key, Object value) {
        SOFT_HASH_MAP.put(key, value);
    }

    /**
     * 基本类型和包装类是否是原始类型
     */
    public static Boolean isPrimitive(Class<?> clazz) {
        try {
            if (clazz.isPrimitive()) {
                return true;
            }
            return clazz.getDeclaredField("TYPE").getClass().isPrimitive();
        } catch (NoSuchFieldException e) {
            return false;
        }

    }

    /**
     * 从方法中
     * 获取parameter——Annotation的映射关系
     */
    public static <A extends Annotation> Map<Object, A> getAnnotationOfMethod(Method method, Class<A> annotationClass) {
        Parameter[] parameters = null;
        if (method == null || annotationClass == null || (parameters = method.getParameters()) == null || parameters.length == 0) {
            return MapUtil.empty();
        }
        String key = "getAnnotationOfMethod" + method.getDeclaringClass().getName() + method.getName() + annotationClass.getName();
        Map<Object, A> map = (Map<Object, A>) getCache(key);
        if (map != null) {
            return map;
        }

        Map<Object, A> newMap = MapUtil.newHashMap(parameters.length);
        for (Parameter parameter : parameters) {
            A annotation = parameter.getAnnotation(annotationClass);
            if (annotation != null) {
                newMap.put(parameter, annotation);
            }
        }

        setCache(key, newMap);
        return newMap;
    }

    /**
     * 通过fileName获取对应的filed实际的值
     */
    public static Object getFiledValues(Object obj, String fieldName) {
        Field filedByName = getFiledByName(obj.getClass(), fieldName);
        return getFieldValue(obj, filedByName);
    }

    /**
     * 先获取filed
     */
    private static Field getFiledByName(Class<?> clazz, String fieldName) {
        if (clazz == null || fieldName == null) {
            return null;
        }
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            log.error("class {} getFiledByName {} error", clazz.getName(), fieldName);
            return null;
        }
    }

    /**
     * 通过filed和get方法获取值
     */
    private static Object getFieldValue(Object obj, Field field) {
        if (obj == null || field == null) {
            return null;
        }
        try {
            Method getMethod = getGetMethod(PrefixMethod.GET, field.getName(), obj.getClass());
            getMethod.setAccessible(true);
            return getMethod.invoke(obj);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("class {} getFieldValue {} error", obj.getClass().getName(), field.getName());
            return null;
        }
    }

    private static Method getGetMethod(PrefixMethod prefixMethod, String fieldName, Class<?> clazz) {
        if (StrUtil.isEmpty(fieldName) || clazz == null) {
            throw new IllegalArgumentException("fieldName or clazz is null");
        }
        StringBuilder methodName = new StringBuilder(prefixMethod.getCode());
        methodName.append(StrUtil.upperFirst(fieldName.substring(0, 1))).append(fieldName.substring(1));

        try {
            return clazz.getMethod(methodName.toString());
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("class " + clazz.getName() + " getMethod " + methodName + " error");
        }
    }

}
