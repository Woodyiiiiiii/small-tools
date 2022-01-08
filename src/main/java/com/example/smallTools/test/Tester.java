package com.example.smallTools.test;

import com.example.smallTools.model.StudentModel;
import com.example.smallTools.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: woodyiiiiiii
 * @Date: 2021/12/31 22:54
 */
@Component
@Slf4j
public class Tester { // implements CommandLineRunner {

    @Resource
    private StudentService studentService;

//    @Override
    public void run(String... args) throws Exception {
//        log.info("Tester info");
//        log.debug("Tester debug");
//        log.error("Tester error");
    }
}
