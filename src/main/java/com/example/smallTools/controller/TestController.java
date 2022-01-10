package com.example.smallTools.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:woody
 * @Date: 2022/1/10 22:21
 */
@RestController
@RequestMapping(value = "/test")
@Slf4j
public class TestController {

    /**
     * postman error:
     * Unable to load data as you’re offline
     * WTF
     */
    @RequestMapping("testRedisLock")
    public void testRedisRock() {
        log.info("testRedisLock");
    }

}
