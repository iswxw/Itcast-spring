package com.wxw.component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ Author ：wxw.
 * @ Date ： 14:34 2020/5/26
 * @ Description：定时任务：隔6秒执行一次
 * @ Version:   v_0.0.1$
 */
@Component
public class TaskBean1 {

    private static volatile int count = 0;

    //每嗝6秒执行一次
    @Scheduled(cron = "*/6 * * * * ?")
    private void process(){
        System.out.println("定时任务执行次数："+ ++count);
    }
}
