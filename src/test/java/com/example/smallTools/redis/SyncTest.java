package com.example.smallTools.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;

/**
 * @Author:woody
 * @Date: 2022/1/23 17:09
 */
@SpringBootTest
@Slf4j
public class SyncTest {

    /**
     * 异步测试
     * 方法终止CompletableFuture也就终止了
     * 所以还是用MQ吧
     */
    @Test
    public void test() {
        CompletableFuture.supplyAsync(() -> {
            log.info("start");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("end 1");
            return 1;
        });
        log.info("end");
    }

}
