package com.wxw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @ Author ：wxw.
 * @ Date ： 9:37 2020/5/27
 * @ Description：Redis使用的学习
 * @ Version:   V 2.0.0$
 */
//@EnableCaching // 开启缓存也可以配置类形式开启
@SpringBootApplication
public class MainRedis {
    public static void main(String[] args) {
        SpringApplication.run(MainRedis.class,args);
    }


}
