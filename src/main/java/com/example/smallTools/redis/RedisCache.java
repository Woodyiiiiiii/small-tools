package com.example.smallTools.redis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RedisCache {

    long TIMEOUT = 60 * 20 * 1000;

    String key() default "";

    DbOperate dbOperate() default DbOperate.QUERY;

    long timeout() default TIMEOUT;

}
