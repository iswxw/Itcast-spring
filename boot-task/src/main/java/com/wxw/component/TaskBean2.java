package com.wxw.component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ Author ：wxw.
 * @ Date ： 14:38 2020/5/26
 * @ Description：定时任务2：延迟任务
 * @ Version:   v_0.0.1$
 */
@Component
public class TaskBean2 {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");
    //每隔6秒运行一次
    @Scheduled(fixedDelay = 6000)
    private void reportCurrentTime(){
        System.out.println("定时任务2: "+DATE_FORMAT.format(new Date()));
    }
}
