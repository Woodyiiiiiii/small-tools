package com.example.smallTools.javabasic.proxy.classloader;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: woodyiiiiiii
 * @Date: 2021/12/29 22:14
 */
public class ClassLoaderTester {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // class relative path
        String className = "com.example.smallTools.proxy.classloader.ClassDemo";

        // create a classloader
        ClassLoader classLoader = ClassLoaderTester.class.getClassLoader();

        // use classloader to load class by class path name
        Class<?> aClass = classLoader.loadClass(className);

        System.out.println(aClass.getName());

        System.out.println("----print fields--------------");

        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }

        System.out.println("----print methods-------");

        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        System.out.println("----construct-------");

        String name = "woodyiiiiiii";
        ClassDemo demo = (ClassDemo)aClass.getConstructor(name.getClass()).newInstance(name);
        demo.sayHello();

    }

}
