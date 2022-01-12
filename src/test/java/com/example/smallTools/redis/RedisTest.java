package com.example.smallTools.redis;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.example.smallTools.model.StudentModel;
import com.example.smallTools.redis.redistemplate.RedisMapCache;
import com.example.smallTools.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Var;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import javax.annotation.PostConstruct;
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

    private DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();

    @PostConstruct
    public void init(){
        redisScript.setResultType(Long.class);
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("redislock.lua")));
    }

    /**
     * 使用lua脚本实现分布式锁
     * 可以保证原子性
     * 可以解决锁误解除的问题
     * 无法解决redis集群时锁被多个线程拿的问题
     */
    @Test
    public void testRedisLockWithLua() {

        // 模拟传来的key
        String key = "12";

        // 生成唯一ID
        String value = IdUtil.simpleUUID();
        log.info("value:{}", value);

        Boolean aBoolean = redisMapCache.setRedisLock(key, value, 20000, TimeUnit.MILLISECONDS);
        if (aBoolean) {
            // 拿到锁
            log.info("{} 拿到锁", key);

            // 模拟业务逻辑
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                log.error("sleep error:{}", e.getMessage());
            } finally {
                // 判断是否是当前锁
                Object currentValue = redisMapCache.getRedisLock(key);
                if (currentValue != null && currentValue.equals(value)) {
                    log.info("key:{}, value:{}", key, JSONUtil.toJsonStr(currentValue));

                    // 获取锁对象的同时释放锁，保证了原子性，不会出现锁误解除的问题
                    Object lua = redisMapCache.getRedisLockByLua(redisScript, Lists.newArrayList(key), value);
                    log.info("结果：1表示执行del，0表示未执行, {}", lua);
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
