package com.wxw.job;

import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

/**
 * @author ：wxw.
 * @date ： 10:21 2020/12/7
 * @description：任务测试
 * @link:
 * @version: v_0.0.1
 */
@Component
public class testTask extends AbstractJob {
    @Override
    protected void executeJob(JobExecutionContext jobContext) throws Exception {
        logger.info("任务删除");
    }
}
