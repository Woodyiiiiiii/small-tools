package com.example.smallTools.javabasic.proxy.classloader;

/**
 * @Author: woodyiiiiiii
 * @Date: 2021/12/29 22:15
 */
public class ClassDemo {

    private String name;

    public ClassDemo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sayHello() {
        System.out.println("Hello, " + name);
    }

}
