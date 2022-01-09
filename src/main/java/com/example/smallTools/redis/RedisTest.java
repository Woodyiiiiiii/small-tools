package com.example.smallTools.redis;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.example.smallTools.model.StudentModel;
import com.example.smallTools.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:woody
 * @Date: 2022/1/9 19:25
 */

@Slf4j
public class RedisTest {

    @Resource
    private StudentService studentService;

    @Test
    public void run(String... args) throws Exception {
        List<StudentModel> modelList = studentService.selectAll();
        if (CollectionUtil.isEmpty(modelList)) {
            log.error("query student all error");
            return;
        }
        modelList.forEach(studentModel -> System.out.print(JSONUtil.toJsonStr(studentModel) + "\n"));
    }
}
