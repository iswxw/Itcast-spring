package com.wxw.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ Author ：wxw.
 * @ Date ： 17:18 2020/6/3
 * @ Description：Redis 中引用封装方法
 * @ Version:   v_0.0.1
 */
@Service
public class RedisService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     *  将指定的元素添加到HyperLogLog中，可以添加多个元素
     * @param key
     * @param value
     */
    public void pfAdd(String key, String... value) {
        stringRedisTemplate.opsForHyperLogLog().add(key, value);
    }

    /**
     * 返回给定HyperLogLog的基数估算值。当一次统计多个HyperLogLog时，需要对多个HyperLogLog结构进行比较，并将并集的结果放入一个临时的HyperLogLog，性能不高，谨慎使用
     * @param key
     * @return
     */
    public Long pfCount(String... key) {
        return stringRedisTemplate.opsForHyperLogLog().size(key);
    }

    /**
     * 将多个HyperLogLog进行合并，将并集的结果放入一个指定的HyperLogLog中
     */
    public void pfMerge(String destKey,String...sourceKey){
        this.stringRedisTemplate.opsForHyperLogLog().union(destKey,sourceKey);
    }



}
