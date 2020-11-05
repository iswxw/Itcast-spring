package com.wxw.job;

import org.quartz.JobExecutionContext;

/**
 * @author ：wxw.
 * @date ： 15:32 2020/11/5
 * @description：测试定时任务
 * @version: v_0.0.1
 */
public class DurableCustomTestJob extends AbstractJob {

    @Override
    protected void executeJob(JobExecutionContext jobContext) throws Exception {
        logger.debug("TestJob定时任务正在执行，参数为：{}", "Hello Quartz");
    }
}
