package com.wxw.task;
import com.wxw.job.AbstractJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @ Author ：wxw.
 * @ Date ： 15:46 2020/5/26
 * @ Description：测试程序  并未持久化
 * @ Version:   v_0.0.1$
 */
public class DurableJobStoreTest {

    public static void main(String[] args) throws SchedulerException {
        // 获取Scheduler
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        JobDetail jobDetail = JobBuilder.newJob(AbstractJob.class)
                .withIdentity("job", "group_01")
                .storeDurably(true)
                .build();
        // 创建 Trigger
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger", "group_01") // 设置标识
                .startNow()
                // 每隔3秒执行一次
                .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
                .build();
        // 关联 job和 trigger
        scheduler.scheduleJob(jobDetail, trigger);
        // 启动 scheduler
        scheduler.start();
    }
}


