package com.example.smallTools.concurrency;


import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


/**
 * @Author: woodyiiiiiii
 * @Date: 2021/12/5 13:06
 */
public class Tester {

    ThreadLocal<String> threadLocal = new ThreadLocal<>();
//
//    static AtomicReference<ConcurrentHashMap> atomicReference = new AtomicReference<>();
//
//    volatile Node[] map = new Node[8];
//
//
//
//    class Node {
//        volatile int val;
//    }


    public static void main(String[] args) throws IllegalAccessException {
////        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
////        Future<String> future = executor.submit(() -> {
////            return "hello";
////        });
//        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
//        ConcurrentHashMap expectedValue = atomicReference.get();
//        boolean b = atomicReference.compareAndSet(expectedValue, map);
//
//        //----A map.put(1,5) (1,4)
//        map.putAll(expectedValue);

        // user reflection to create Unsafe
//        Class<Unsafe> aClass = Unsafe.class;
//        Field declaredField = aClass.getDeclaredFields()[1];
//        declaredField.setAccessible(true);
//        Unsafe unsafe = (Unsafe) declaredField.get(null);
//        System.out.println(unsafe);
    }

}
