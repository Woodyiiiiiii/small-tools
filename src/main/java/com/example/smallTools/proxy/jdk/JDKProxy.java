package com.example.smallTools.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: woodyiiiiiii
 * @Date: 2021/12/28 23:13
 */
public class JDKProxy {

    private Object target;

    public JDKProxy(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), (proxy, method, args) -> {
            if (method.getDeclaringClass().isAssignableFrom(target.getClass())) {
                System.out.println("test");
                return method.invoke(target, args);
            }
            System.out.println("JDKProxyHello before");
            Object invoke = method.invoke(target, args);
            System.out.println("JDKProxyHello after");
            return invoke;
        });
    }

}
