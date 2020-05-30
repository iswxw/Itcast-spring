package com.wxw.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisListCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ Author ：wxw.
 * @ Date ： 18:12 2020/5/30
 * @ Description：Redis 存储列表
 * @ Version:   v_0.01$
 */
@Slf4j
@SpringBootTest
public class RedisList {



    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 参考地址：
     * https://www.cnblogs.com/heavenplus/p/11936027.html
     */
    @Test
    public void testDate1(){

        //删除list以便反复测试（）
        redisTemplate.delete("list");
        //将node3从左边插入链表（lpush）
        redisTemplate.opsForList().leftPush("list","Node3");

        List<String> nodeList = new ArrayList<>(5);
        for(int i=2;i>=1;i--) {
            nodeList.add("node" + i);
        }
        // 将多个node从左边插入链表（lpush）
        Long len = redisTemplate.opsForList().leftPushAll("list", nodeList);
        System.out.println("len = " + len);

        //从链表右边插入一个node(rpush)
        redisTemplate.opsForList().rightPush("list","node4");

        //获取下标为0的节点（lindex）
        String node1 = (String) redisTemplate.opsForList().index("list", 1);
        log.info("获取下标为1的节点：",node1);

        //获取链表长度（llen）
        Long size = redisTemplate.opsForList().size("list");
        log.info("获取链表长度（llen）==>{}",size);

        //从左边弹出一个节点（lpop）
        String lpop = (String) redisTemplate.opsForList().leftPop("list");
        log.warn("从左边弹出一个节点（lpop）==>{}",lpop);

        //从右边弹出一个节点（rpop）
        String rpop = (String) redisTemplate.opsForList().rightPop("list");
        log.info("从右边弹出一个节点（rpop）==>{}"+rpop);


        try {
            //在node2前插入一个节点（linsert 需要使用更为底层的命令操作）
            redisTemplate.getConnectionFactory().getConnection().lInsert("list".getBytes("utf-8"),
                    RedisListCommands.Position.BEFORE,"node2".getBytes("utf-8"),"before_node".getBytes("utf-8"));
            //在node2之后插入一个节点（linsert 需要使用更为底层的命令操作）
            redisTemplate.getConnectionFactory().getConnection().lInsert("list".getBytes("utf-8"),
                    RedisListCommands.Position.AFTER,"node2".getBytes("utf-8"),"after_node".getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            log.error("不支持编码解析操作！！");
        }

        //判断list是否存在，若存在则在左侧插入节点（lpushx）
        redisTemplate.opsForList().leftPushIfPresent("list","start");
        //判断list是否存在，若存在则在右侧插入节点（rpushx）
        redisTemplate.opsForList().rightPushIfPresent("list","end");
        //获取下标0到3的节点元素(lrange)
        List<String> ranges = redisTemplate.opsForList().range("list", 0, 10);
        ranges.forEach(rang-> System.out.println(rang));

        for(int i=0;i<3;i++){
            nodeList.add("node"+i);
        }
        //从左侧插入3个值为node的节点（lpush）
        redisTemplate.opsForList().leftPushAll("list",nodeList);
        //从左到右删除至多三个值为node的节点（lrem）
        redisTemplate.opsForList().remove("list",3,"node");
        //给链表下标为0的节点设置新值（lset）
        redisTemplate.opsForList().set("list",0,"new_node_value");
        log.info("nodeList==="+redisTemplate.opsForList().range("list",0,redisTemplate.opsForList().size("list")));

    }
}
