package com.wxw;

import com.wxw.common.tools.ScheduleUtils;
import com.wxw.domain.ScheduleJob;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.Scheduler;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DurableCustomTestJob {

    @Resource
    private Scheduler scheduler;

    private void buildTestJob(ScheduleJob scheduleJob) {
        scheduleJob.setBeanName("DurableCustomTestJob");
    }


    @Before
    public void testBefore() {
    }

    @Test
    public void testJob(){
        ScheduleJob scheduleJob = new ScheduleJob();
        scheduleJob.setParams("定时任务参数");
        scheduleJob.setRemark("定时任务备注");
        scheduleJob.setCronExpression("0 0/5 * * * ? ");
        buildTestJob(scheduleJob);
//        ScheduleUtils.createScheduleJob(scheduler,scheduleJob);
        ScheduleUtils.deleteScheduleJob(scheduler,1L);
    }

    @After
    public void testAfter() {
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
