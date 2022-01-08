package com.example.smallTools.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description: 为何报错？学完生命周期回答 The dependencies of some of the beans in the application context form a cycle:
 * @Author: woodyiiiiiii
 * @Date: 2022/1/3 16:58
 */
@Component
public class SpringDemo implements ApplicationContextAware, InitializingBean, CommandLineRunner {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 报错
     */
    @Override
    public void afterPropertiesSet() throws Exception {
//        SpringDemo springDemo = applicationContext.getBean(SpringDemo.class);
//        System.out.println(springDemo);
    }

    /**
     * 不报错
     */
//    @Override
    public void run(String... args) throws Exception {
//        SpringDemo springDemo = applicationContext.getBean(SpringDemo.class);
//        System.out.println(springDemo);
    }

    @Override
    public String toString() {
        return "SpringDemo{" +
                "applicationContext=" + applicationContext +
                '}';
    }
}
