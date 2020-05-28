package com.wxw.redis;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    /**
     * set 操作
     * SET key value [EX seconds] [PX milliseconds] [NX|XX]
     */
    @Test
    public void test1() {
        // 过期时间为永久
        this.stringRedisTemplate.opsForValue().set("k1", "012345678");
        // 覆盖从指定位置开始的值  此时k1= 0v1345678
        this.stringRedisTemplate.opsForValue().set("k1", "v1", 1);
        // k-v-过期时间-过期时间单位
        this.stringRedisTemplate.opsForValue().set("k3", "v3", 5, TimeUnit.HOURS);
        //在原有的值基础上新增字符串到末尾。
        this.stringRedisTemplate.opsForValue().append("k3", "_v4");
        // 获取指定字符串的长度
        Long k3 = this.stringRedisTemplate.opsForValue().size("k3");
        System.out.println("k3 = " + k3);

        // 以增量的方式将long值存储在变量中
        Long k5 = this.stringRedisTemplate.opsForValue().increment("k5", 1L);
        System.out.println("k5 = " + k5);
        Double k6 = this.stringRedisTemplate.opsForValue().increment("k6", 3d);
        System.out.println("k6 = " + k6);
        //以增量的方式将long值存储在变量中  默认是1
        Long k7 = this.stringRedisTemplate.opsForValue().increment("k7");
        Long k8 = this.stringRedisTemplate.opsForValue().increment("k7");
        System.out.println("k7 = " + k7);
        System.out.println("k8 = " + k8);

        // 如果键不存在则新增,存在则不改变已经有的值。
        Boolean k9 = this.stringRedisTemplate.opsForValue().setIfAbsent("k9", "k9");
        System.out.println("k9 = " + k9);

        // 设置map集合到redis。
        Map valueMap = new HashMap();
        valueMap.put("valueMap1", "map1");
        valueMap.put("valueMap2", "map2");
        valueMap.put("valueMap3", "map3");
        stringRedisTemplate.opsForValue().multiSet(valueMap);

        //根据集合取出对应的value值。
        List paraList = new ArrayList();
        paraList.add("valueMap1");
        paraList.add("valueMap2");
        paraList.add("valueMap3");
        List<String> valueList = stringRedisTemplate.opsForValue().multiGet(paraList);
        System.out.println("valueList = " + valueList);
    }

    // Hash 数据结构
    @Test
    public void test2() {

    }


}
