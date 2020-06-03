package com.wxw.redis;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * @ Author ：wxw.
 * @ Date ： 16:24 2020/6/3
 * @ Description：HyperLogLog 基数统计的算法
 * @ Version:   v_0.0.1
 */
@SpringBootTest
public class RedisHyperLogLog {

    @Resource
    private StringRedisTemplate redisTemplate;

    @Test
    public void testData1(){
        //将指定的元素添加到HyperLogLog中，可以添加多个元素
        Long hyperKey_01 = redisTemplate.opsForHyperLogLog().add("k1", "v1");
        System.out.println("hyperKey_01 = " + hyperKey_01);
        // 返回给定HyperLogLog的基数估算值。当一次统计多个HyperLogLog时，需要对多个HyperLogLog结构进行比较，并将并集的结果放入一个临时的HyperLogLog，性能不高，谨慎使用
        Long size = redisTemplate.opsForHyperLogLog().size("k1");
        System.out.println("size = " + size);
        // 删除key
        // redisTemplate.opsForHyperLogLog().delete("k1");

        //将多个HyperLogLog进行合并，将并集的结果放入一个指定的HyperLogLog中
        //Long union = redisTemplate.opsForHyperLogLog().union("destKey", "sourceKey");


    }




}
