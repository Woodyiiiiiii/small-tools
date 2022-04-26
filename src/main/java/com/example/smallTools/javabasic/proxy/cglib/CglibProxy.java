package com.example.smallTools.javabasic.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: woodyiiiiiii
 * @Date: 2021/12/28 23:44
 */
public class CglibProxy implements MethodInterceptor {

    Object object;

    public CglibProxy(Object object) {
        this.object = object;
    }

    public Object getProxyInstance() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(object.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib before");
        if (method.getName().equals("sayHello")) {
            System.out.println("test");
        }
        Object invoke = method.invoke(object, objects);
        System.out.println("cglib after");
        return invoke;
    }
}
