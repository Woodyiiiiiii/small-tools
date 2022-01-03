package com.example.smallTools.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description: 为何报错？学完生命周期回答
 * @Author: woodyiiiiiii
 * @Date: 2022/1/3 16:58
 */
@Component
public class SpringDemo implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        SpringDemo springDemo = applicationContext.getBean(SpringDemo.class);
        System.out.println(springDemo);
    }
}
