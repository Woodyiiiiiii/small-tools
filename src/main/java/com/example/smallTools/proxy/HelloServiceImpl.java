package com.example.smallTools.proxy;

/**
 * @Author: woodyiiiiiii
 * @Date: 2021/12/28 23:03
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        System.out.println("Hello " + name);
        return "Hello " + name;
    }

}
