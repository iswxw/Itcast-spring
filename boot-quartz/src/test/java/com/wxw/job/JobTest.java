package com.wxw.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 新建执行的任务
 */
public class JobTest implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        System.out.println("jobExecutionContext = " + jobExecutionContext);
        System.out.println("任务正在执行...");

    }
}
