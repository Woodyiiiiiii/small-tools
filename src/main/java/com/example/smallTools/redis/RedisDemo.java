package com.example.smallTools.redis;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.example.smallTools.model.StudentModel;
import com.example.smallTools.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:woody
 * @Date: 2022/1/9 19:25
 */
@Component
@Slf4j
public class RedisDemo implements CommandLineRunner {

    @Resource
    private StudentService studentService;

    @Override
    public void run(String... args) throws Exception {
        List<StudentModel> modelList = studentService.selectAll();
        if (CollectionUtil.isEmpty(modelList)) {
            log.error("query student all error");
            return;
        }
        modelList.forEach(studentModel -> System.out.print(JSONUtil.toJsonStr(studentModel) + "\n"));
    }
}
