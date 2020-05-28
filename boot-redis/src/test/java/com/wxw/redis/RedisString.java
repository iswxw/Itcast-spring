package com.wxw.redis;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * @ Author ：wxw.
 * @ Date ： 18:30 2020/5/27
 * @ Description：Redis基本数据功能测试
 * @ Version:   v_0.0.1$
 */
@SpringBootTest
public class RedisString {

    private final static String json = "{\"courseName\":\"空乘英语\",\"score\":\"1\"}";

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * String 类型的基本操作
     */
    @Test
    public void test1() {
        stringRedisTemplate.opsForValue().set("wxw", "xkw");
        String key_wxw = stringRedisTemplate.opsForValue().get("wxw");
        System.out.println("key_wxw = " + key_wxw);
    }

    @Test
    public void test2(){
        redisTemplate.opsForValue().set("wxw", "xkw");
        Object key_wxw = redisTemplate.opsForValue().get("wxw");
        System.out.println("key_wxw = " + key_wxw);
    }


}
