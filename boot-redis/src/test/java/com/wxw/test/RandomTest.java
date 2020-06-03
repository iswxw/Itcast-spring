package com.wxw.test;

import cn.hutool.core.lang.Console;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

/**
 * @ Author ：wxw.
 * @ Date ： 17:06 2020/6/3
 * @ Description：随机数的类测试
 * @ Version:   v_0.0.1
 */
@SpringBootTest
public class RandomTest {

    @Test
    public void testData1(){
        Random random = new Random();
        // 生成任意整数
        int a = random.nextInt();
        System.out.println("a = " + a);

        // 生成 [0,10) 之间的 随机整数
        int b = random.nextInt(10);
        System.out.println("生成 [0,10) 之间的 随机整数 = " + b);


    }
}
