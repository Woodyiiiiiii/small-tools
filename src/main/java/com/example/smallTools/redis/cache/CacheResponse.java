package com.example.smallTools.redis.cache;

import cn.hutool.core.util.ObjectUtil;
import lombok.Getter;

/**
 * @Author:woody
 * @Date: 2022/1/8 21:17
 */
public final class CacheResponse<V, Err extends Exception> {

    @Getter
    private final boolean hasKey;

    @Getter
    private final V value;

    @Getter
    private final Err exception;

    public CacheResponse(boolean hasKey, V value, Err exception) {
        this.hasKey = hasKey;
        this.value = value;
        this.exception = exception;
    }

    public boolean isSucceed() {
        return exception != null;
    }

    public static <V, Err extends Exception> CacheResponse<V, Err> success(boolean hasKey, V value) {
        return new CacheResponse<>(hasKey, value, null);
    }

    public static <V, Err extends Exception> CacheResponse<V, Err> fail(Err exception) {
        return new CacheResponse<>(false, null, exception);
    }

}
