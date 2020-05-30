package com.wxw.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Set;

/**
 * @ Author ：wxw.
 * @ Date ： 18:43 2020/5/30
 * @ Description：RedisSet测试用例
 * @ Version:   v_0.0.1$
 */
@Slf4j
@SpringBootTest
public class RedisSet {


    /**
     * 参考教程：
     * https://www.jianshu.com/p/4024e9b9ba93?utm_campaign=maleskine&utm_content=note&utm_medium=seo_notes&utm_source=recommendation
     */

    @Resource
    private RedisTemplate redisTemplate;

    private static final String key= "set:key=";

    @Test
    public void test(){

        Long len = redisTemplate.opsForSet().add(key, "wxw");
        printSet(key);

        //集合中添加元素，返回添加个数
        Long addcount = redisTemplate.opsForSet().add("games", "鬼泣", "古墓丽影", "仙剑奇侠传", "LOL", "DOTA自走棋");
        log.info("集合中添加元素，返回添加个数==>{}",addcount);

        //从集合中删除指定元素
        redisTemplate.opsForSet().remove("games", "鬼泣");

        //从集合中随机删除一个元素，并返回该元素
        String item = (String) redisTemplate.opsForSet().pop("games");
        log.info("从集合中随机删除一个元素，并返回该元素==>{}",item);

        //将集合1中的指定元素移到集合2
        redisTemplate.opsForSet().move("games", "仙剑奇侠传", "chinese-games");
        //打印集合1
        printSet("games");
        //打印集合2
        printSet("chinese-games");

        //获得集合大小
        Long size = redisTemplate.opsForSet().size("games");
        printSet("games");

        //判断集合中是否存在某元素
        Boolean ifExist = redisTemplate.opsForSet().isMember("games", "LOL");

        //添加集合a
        redisTemplate.opsForSet().add("set-a", "a", "b", "c", "d");
        //添加集合b
        redisTemplate.opsForSet().add("set-b", "a", "b");
        //求交集
        Set<String> intersection = redisTemplate.opsForSet().intersect("set-a", "set-b");
        //也可以和多个key对应的集合求交集                  Set<V> intersect(K key, K otherKey);
        System.out.println(intersection);


        //添加集合a
        redisTemplate.opsForSet().add("set-a", "a", "b", "c", "d");
        //添加集合b
        redisTemplate.opsForSet().add("set-b", "a", "b");
        //求交集并放入集合c
        redisTemplate.opsForSet().intersectAndStore("set-a", "set-b", "set-c");
        //也可以和多个key对应的集合求交集                 Long intersectAndStore(K key, Collection<K> otherKeys, K destKey);
        //打印集合c
        printSet("set-c");

    }

    @Test
    public void demo9() {
        //添加集合m
        redisTemplate.opsForSet().add("set-m", "a", "b", "c", "d");
        //添加集合n
        redisTemplate.opsForSet().add("set-n", "c", "d", "e", "f");
        //求并集
        Set<String> union = redisTemplate.opsForSet().union("set-m", "set-n");
        System.out.println(union);

        //其他操作与intersect类似 如和多个key求并集，求并集并放入集合等
    }

    @Test
    public void demo10() {
        //添加集合d
        redisTemplate.opsForSet().add("set-d", "a", "b", "c", "d");
        //添加集合e
        redisTemplate.opsForSet().add("set-e", "c", "d", "e", "f");
        //添加集合f
        redisTemplate.opsForSet().add("set-f", "e", "f", "g", "h");

        //求差集(属于d不属于e和f)
        Set<String> difference = redisTemplate.opsForSet().difference("set-d", Arrays.asList("set-e", "set-f"));
        System.out.println(difference);
        //其他操作与交集并集类似
    }

    @Test
    public void demo11() {
        //随机获取集合中的一个元素
        System.out.println(redisTemplate.opsForSet().randomMember("games"));

        //随机获取集合中指定个数的元素（有可能重复）
        System.out.println(redisTemplate.opsForSet().randomMembers("games", 10));

        //随机获取集合中指定个数的元素（不重复）
        System.out.println(redisTemplate.opsForSet().distinctRandomMembers("games", 10));


    }


    /**
     *  打印集合
     * @param key
     */
    private void printSet(String key) {
        //获取所有成员
        Set<String> members = redisTemplate.opsForSet().members(key);
        System.out.println(members);
    }

}
