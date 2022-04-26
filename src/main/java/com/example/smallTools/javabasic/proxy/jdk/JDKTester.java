package com.example.smallTools.javabasic.proxy.jdk;

import com.example.smallTools.javabasic.proxy.HelloService;
import com.example.smallTools.javabasic.proxy.HelloServiceImpl;

/**
 * @Description: JDK动态代理示例
 * @Author: woodyiiiiiii
 * @Date: 2021/12/28 23:06
 */
public class JDKTester {

    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        System.out.println(helloService.getClass());
        helloService.sayHello("woody");
        HelloService proxyHelloService = (HelloService) new JDKProxy(helloService).getProxyInstance();
        System.out.println(proxyHelloService.getClass());
        proxyHelloService.sayHello("woody");
    }

}
