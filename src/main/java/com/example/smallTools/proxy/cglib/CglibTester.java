package com.example.smallTools.proxy.cglib;

import com.example.smallTools.proxy.HelloService;
import com.example.smallTools.proxy.HelloServiceImpl;

/**
 * @Author: woodyiiiiiii
 * @Date: 2021/12/28 23:47
 */
public class CglibTester {

    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        HelloService helloServiceProxy = (HelloService) new CglibProxy(helloService).getProxyInstance();
        helloServiceProxy.sayHello("woody");
    }

}
