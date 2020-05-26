package com.wxw.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDate;


/**
 * @ Author ：wxw.
 * @ Date ： 15:41 2020/5/26
 * @ Description：持久化的任务测试
 * @ Version:   v_0.0.1$
 */

public class DurableCustomJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("定时任务正在执行[每5秒执行一次]："+ LocalDate.now());
    }
}
