package com.example.smallTools.redis;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.example.smallTools.model.StudentModel;
import com.example.smallTools.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:woody
 * @Date: 2022/1/9 19:54
 */
@Slf4j
@SpringBootTest
public class RedisTest {

    @Resource
    private StudentService studentService;

    @Test
    public void testQuery() {
        List<StudentModel> modelList = studentService.selectAll();
        if (CollectionUtil.isEmpty(modelList)) {
            log.error("query student all error");
            return;
        }
        modelList.forEach(studentModel -> System.out.print(JSONUtil.toJsonStr(studentModel) + "\n"));
    }

    @Test
    public void testUpdate() {
        StudentModel studentModel = new StudentModel();
        studentModel.setId(1L);
        studentModel.setName("test");
        studentService.updateByOneId(studentModel);
    }

}
