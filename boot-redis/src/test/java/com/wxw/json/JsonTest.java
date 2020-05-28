package com.wxw.json;

import com.alibaba.fastjson.JSON;
import com.wxw.MainRedis;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ Author ：wxw.
 * @ Date ： 18:03 2020/5/27
 * @ Description：JSON字符串转换测试
 * @ Version:   v_0.0.1$
 */
//@SpringBootTest(classes = {MainRedis.class})
@SpringBootTest
public class JsonTest {

    private final static String json = "{\"courseName\":\"空乘英语\",\"score\":\"1\"}";

    @Test
    public void test(){
        Object parse = JSON.parse(json);
        System.out.println(parse);
    }

}
