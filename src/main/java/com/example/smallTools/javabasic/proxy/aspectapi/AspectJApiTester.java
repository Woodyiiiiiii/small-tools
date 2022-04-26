package com.example.smallTools.javabasic.proxy.aspectapi;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Spring中的AspectJ代理
 * @Author: woodyiiiiiii
 * @Date: 2021/12/29 22:31
 */
public class AspectJApiTester {

    public static void main(String[] args) {

        Map<String, Object> map = new HashMap<>();

        // create a proxy factory
        AspectJProxyFactory factory = new AspectJProxyFactory(map);

        // add the aspect
        factory.addAspect(AspectConfiguration.class);

        // add advice
        factory.addAdvice((MethodBeforeAdvice) (method, args1, target) -> {
            if (method.getName().equals("put") && args1.length == 2) {
                System.out.println("before put");
                System.out.printf("key: %s, value: %s\n", args1[0], args1[1]);
            }
        });

        // create the proxy
        Map<String, Object> proxy = factory.getProxy();

        // use the proxy
        proxy.put("key", "value");

    }

}
