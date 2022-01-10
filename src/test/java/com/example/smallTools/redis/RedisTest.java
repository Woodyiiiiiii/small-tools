package com.example.smallTools.redis;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.example.smallTools.model.StudentModel;
import com.example.smallTools.redis.redistemplate.RedisMapCache;
import com.example.smallTools.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Var;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author:woody
 * @Date: 2022/1/9 19:54
 */
@Slf4j
@SpringBootTest
public class RedisTest {

    @Resource
    private StudentService studentService;
    @Resource
    private RedisMapCache redisMapCache;

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

    @Test
    public void testUUID() {
        String uuid = IdUtil.simpleUUID();
        log.info("uuid:{}", uuid);
    }

    /**
     * redis分布式锁
     * 无法保证原子性
     * 无法解决锁误解除、超时并发的问题
     *
     * redis set命令已经在2.6.12 版本后整合了setnx功能
     */
    @Test
    public void testRedisLock() {

        // 模拟传来的key
        String key = "12";

        // 生成唯一ID
        String value = IdUtil.simpleUUID();
        log.info("value:{}", value);

        Boolean aBoolean = redisMapCache.setRedisLock(key, value, 20000, TimeUnit.MILLISECONDS);
        if (aBoolean) {
            // 拿到锁
            // 模拟业务逻辑
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                log.error("sleep error:{}", e.getMessage());
            } finally {
                // 判断是否是当前锁
                Object currentValue = redisMapCache.getRedisLock(key);
                if (currentValue != null && currentValue.equals(value)) {
                    // 释放锁
                    redisMapCache.delete(key);
                    log.info("{} 释放锁成功", key);
                } else {
                    // 一个锁超时的线程的分支
                    log.error("daf");
                }
            }
        } else {
            log.info("{} 获取锁失败", key);
        }

    }

}
