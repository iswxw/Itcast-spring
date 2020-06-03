package com.wxw.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * @ Author ：wxw.
 * @ Date ： 8:56 2020/6/3
 * @ Description：单元测试设置临时值
 * @ Version:   v_0.0.1
 */
@SpringBootTest
public class JmeterTest {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testData1(){

        // 设置秒杀库存
        this.stringRedisTemplate.opsForValue().set("stock","100");
    }

}
