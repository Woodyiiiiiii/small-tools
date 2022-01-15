package com.example.smallTools.controller;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;

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

    /**
     * TODO:
     * receive excel file
     */
    @PostMapping("/receiveExcel")
    public void uploadExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        log.info("receive excel file:{}", JSONUtil.toJsonStr(file));

//        InputStream inputStream = file.getInputStream();

    }

}
