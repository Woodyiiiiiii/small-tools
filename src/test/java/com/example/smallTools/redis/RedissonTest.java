package com.example.smallTools.redis;

import cn.hutool.json.JSONUtil;
import com.example.smallTools.redis.redistemplate.RedisMapCache;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Description: RedissonTest
 * @Author:woody
 * @Date: 2022/1/12 23:10
 */
@SpringBootTest
@Slf4j
public class RedissonTest {

    @Resource
    private Redisson redisson;

    @Resource
    private RedisMapCache redisMapCache;

    @Test
    public void test() {

        String lockKey = "lockKey";

        // 加锁
        RLock rLock = redisson.getLock(lockKey);
        log.info("尝试获取锁:{}", JSONUtil.toJsonStr(rLock));

        try {
            // 加锁
            rLock.lock();
            log.info("加锁成功");

            // 模拟业务逻辑
            Object value = redisMapCache.getValue(lockKey);
            log.info("获取到的值:{}", JSONUtil.toJsonStr(value));
            redisMapCache.setValue(lockKey, "value");

        } finally {
            // 释放锁
            rLock.unlock();
        }

    }

}
