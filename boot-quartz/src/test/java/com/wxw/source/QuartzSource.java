package com.wxw.source;

import org.junit.Test;
import org.quartz.*;
import org.quartz.core.JobRunShell;
import org.quartz.core.QuartzSchedulerThread;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.jdbcjobstore.JobStoreTX;
import org.quartz.impl.jdbcjobstore.Semaphore;
import org.quartz.spi.ThreadPool;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 源码参考:https://my.oschina.net/OutOfMemory/blog/1800560
 * 源码流程：https://www.jianshu.com/p/3f77224ad9d4
 * 经典总结：https://blog.csdn.net/bubei/article/details/2108778
 * @author ：wxw.
 * @date ： 16:30 2020/11/10
 * @description：quartz 的源码
 * @version: v_0.0.1
 */
@SpringBootTest
public class QuartzSource {

    @Test
    public void testData1() throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        // QuartzSchedulerThread
        // ThreadPool
        // JobRunShell
        // Semaphore  // 分布式锁
        // JobStoreTX
        // StatefulJob  // 阻止job并发执行
    }
}
