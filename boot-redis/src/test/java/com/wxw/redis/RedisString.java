package com.wxw.redis;

import com.wxw.domain.Address;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.*;
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
     * Key的基本操作
     */
    @Test
    public void test0(){
        this.stringRedisTemplate.opsForValue().set("key1","value1",5,TimeUnit.MINUTES);
         // 删除key
        Boolean key1 = this.stringRedisTemplate.delete("key1");
        System.out.println("key1 = " + key1);
        // 给已有key 重命名
        this.stringRedisTemplate.opsForValue().set("key1","value1",5,TimeUnit.MINUTES);
        this.stringRedisTemplate.rename("key1","key2");
        // 序列化给定的key
        this.stringRedisTemplate.opsForValue().set("wxw","Redis是基于内存的，String的Value 可以存512MB");
        byte[] wxws = this.stringRedisTemplate.dump("wxw");
        System.out.println("序列化wxws = " + wxws);

        // 检测给定key是否存在
        Set<String> keys = this.stringRedisTemplate.keys("*e*");
        keys.forEach(key-> System.out.println(key));

        Boolean key2 = this.stringRedisTemplate.persist("key2");
        System.out.println("key2 = " + key2);
        Long key21 = this.stringRedisTemplate.getExpire("key2");
        System.out.println("key21 = " + key21);
        // 反序列化给定的key
        //this.stringRedisTemplate.restore("wxw","[B@13a6726".getBytes(),5,TimeUnit.MINUTES);
    }

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

    /**
     * hash 类型的数据结构
     */
    @Test
    public void test2() {
        // 新增hash
        this.redisTemplate.opsForHash().put("user","id","001");
        this.redisTemplate.opsForHash().put("user","name","魏小伟");
        this.redisTemplate.opsForHash().put("user","age", new Date());
        this.redisTemplate.opsForHash().put("user","birthday", LocalDate.now());
        List users = this.redisTemplate.opsForHash().values("user");
        users.forEach(user-> System.out.println(user));



    }

    @Test
    public void test11(){

    }


}
