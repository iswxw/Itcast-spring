package com.wxw.redis;

import com.wxw.domain.Address;
import com.wxw.domain.Person;
import com.wxw.tools.ToolsJson;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.*;

/**
 * @ Author ：wxw.
 * @ Date ： 17:31 2020/5/28
 * @ Description：Redis的hash类型的测试
 * @ Version:   v_0.0.1$
 */
@SpringBootTest
public class RedisHash {

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 参考地址
     * https://www.cnblogs.com/heavenplus/p/11935169.html
     */

    private final String key="hash";
    @Test
    public void testData1(){
        Address address = new Address(1L,"甘肃省","北京市","房山区","甘肃省 北京市 房山区");
        Person person = new Person(1L, "刘备", 18, new Date(), LocalDate.now(), address);

        // 将对象存入hash中
        redisTemplate.opsForHash().put(key,"person",person);

        Object person1 = redisTemplate.opsForHash().get(key, "person");
        System.out.println("person1 = " + person1);

    }
    
    @Test
    public void testData2(){

        Map<String,String> map = new HashMap<>();
        map.put("k1","val1");
        map.put("k2","val2");

        //为hash结构设置多个键值对(hmset)
        redisTemplate.opsForHash().putAll(key,map);

        //获取hash结构指定字段的value，单个key(hget)
        Object k1 = redisTemplate.opsForHash().get(key, "k1");
        System.out.println("k1 = " + k1);

        //为hash结构设置单个键值对key(hset)
        redisTemplate.opsForHash().put(key,"k3","val3");
        Object k3 = redisTemplate.opsForHash().get(key, "k3");
        System.out.println("k3 = " + k3);

        //判断hash结构中是否包含某个字段（hexists）
        Boolean has_k2 = redisTemplate.opsForHash().hasKey(key, "k2");
        System.out.println("has_k2 = " + has_k2);

        // 获取hash结构中所有的键值对(hgetall)
        Map entries = redisTemplate.opsForHash().entries(key);
        entries.forEach((k,v)->{
            System.out.println(k+ ":"+v);
        });

        // 获取hash结构中所有的key(hkeys)
        Set keys = redisTemplate.opsForHash().keys(key);
        keys.forEach(key-> System.out.println(key));

        // 获取hash结构中所有的values(hvals)
        List values = redisTemplate.opsForHash().values(key);
        values.forEach(value-> System.out.println(value));

        // 获取hash结构中指定的key的value 可以是多个key(hmget)
        List vals = redisTemplate.opsForHash().multiGet(key, values);
        vals.forEach(val-> System.out.println(val));

        // hash结构中若存在相应的key才进行相应的操作（hsetnx）
        Boolean k1_absent = redisTemplate.opsForHash().putIfAbsent(key, "k1", "99");
        System.out.println("k1_absent = " + k1_absent);

        // 删除hash结构中指定的key(hdel)
        Long delete = redisTemplate.opsForHash().delete(key, "k1", "k2");
        System.out.println("delete = " + delete);
    }

    @Test
    public void testData3(){

    }
    




























}
