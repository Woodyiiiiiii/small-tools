package com.example.smallTools.test;

import com.example.smallTools.model.StudentModel;
import com.example.smallTools.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: woodyiiiiiii
 * @Date: 2021/12/31 22:54
 */
@Component
public class Tester implements CommandLineRunner {

    @Resource
    private StudentService studentService;

    @Override
    public void run(String... args) throws Exception {
        List<StudentModel> studentModelList = studentService.selectAll();
        studentModelList.forEach(System.out::println);
    }
}
