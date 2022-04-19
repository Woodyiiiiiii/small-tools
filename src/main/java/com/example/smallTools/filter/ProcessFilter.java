package com.example.smallTools.filter;

/**
 * @Author:woody
 * @Date: 2022/4/19 23:05
 */
public interface ProcessFilter<T> {

    /**
     * 过滤
     */
    public T filter(T t);

}
