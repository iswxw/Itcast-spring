package com.wxw.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

/**
 * 任务的执行者
 */
public class ScheduleTest {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleTest.class);
    public static void main(String[] args) {
        try {
            // 1. 创建调度器 Schedule
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();

            // 2. 创建JobDetail实例，并与任务进行绑定执行具体的工作
            JobDetail jobDetail = JobBuilder.newJob(JobTest.class).withIdentity("job1", "group1").build();

            // 3，创建触发器，设置间隔1秒执行一次
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1", "triggerGroup1")
                    .startNow() // 立即生效
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(2)  // 每2秒执行一次
                            .repeatForever())  //一直执行
                    .build();

            //4,执行该定时任务
            scheduler.scheduleJob(jobDetail,trigger);
            System.out.println("启动执行-----start");
            scheduler.start();

            // 5, 睡眠
            TimeUnit.MINUTES.sleep(1);
            scheduler.shutdown();
            System.out.println("结束执行------end");


        } catch (SchedulerException e) {
           logger.info("测试任务,任务调度发生异常！");
        } catch (InterruptedException e) {
            logger.info("睡眠异常捕捉");
        }
    }
}
