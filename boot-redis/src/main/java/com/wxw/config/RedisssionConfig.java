package com.wxw.config;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author: wxw
 * @create: 2020-06-02-21:54
 */
@Component
public class RedisssionConfig {
    @Bean
    public Redisson redission(){
        // 单机模式
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379").setDatabase(3);
        return (Redisson)Redisson.create(config);
    }
}
