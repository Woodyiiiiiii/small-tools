package com.example.smallTools.config;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:woody
 * @Date: 2022/1/12 23:10
 */
@Configuration
public class RedissonConfig {

    @Bean
    public Redisson redisson() {
        Config config = new Config();

        // 1.single server
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setDatabase(0);

        // 2.sentinel server
//         config.useSentinelServers()

        // 3.cluster server
//         config.useClusterServers()

        return (Redisson) Redisson.create(config);
    }

}
