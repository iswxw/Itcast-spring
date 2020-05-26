package com.wxw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ Author ：wxw.
 * @ Date ： 14:21 2020/5/26
 * @ Description：task的定时任务启动类
 * @ Version:   v_0.0.1$
 */
@SpringBootApplication
@EnableScheduling   // 开启定时
public class TaskMain8002 {
    public static void main(String[] args) {
        SpringApplication.run(TaskMain8002.class,args);
    }
}
