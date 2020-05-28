package com.wxw.redis;

import com.wxw.domain.Address;
import com.wxw.domain.Person;
import com.wxw.tools.ToolsID;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Date;

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

    @Test
    public void testData1(){
        Address address = new Address(1L,"甘肃省","北京市","房山区","甘肃省 北京市 房山区");
        Person person = new Person(1L, "刘备", 18, new Date(), LocalDate.now(), address);
        System.out.println(person);
    }


}
