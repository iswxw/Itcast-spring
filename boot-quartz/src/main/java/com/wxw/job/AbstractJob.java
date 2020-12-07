package com.wxw.job;

import com.wxw.common.config.ApplicationContextTools;
import com.wxw.domain.ScheduleJob;
import com.wxw.domain.ScheduleJobLog;
import com.wxw.service.IScheduleJobLogService;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.StopWatch;

import java.time.LocalDateTime;

/**
 * @ Author ：wxw.
 * @ Date ： 15:41 2020/5/26
 * @ Description：持久化的任务测试
 * @ Version:   v_0.0.1$
 */
public abstract class AbstractJob extends QuartzJobBean {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void executeInternal(JobExecutionContext jobContext) throws JobExecutionException {
        ScheduleJob scheduleJob = (ScheduleJob) jobContext.getMergedJobDataMap().get(ScheduleJob.JOB_PARAM_KEY);
        //获取spring bean
        IScheduleJobLogService scheduleJobLogService = (IScheduleJobLogService) ApplicationContextTools.getBean("scheduleJobLogService");
        //数据库保存执行记录
        ScheduleJobLog jobLog = new ScheduleJobLog();
        jobLog.setJobId(scheduleJob.getJobId());
        jobLog.setBeanName(scheduleJob.getBeanName());
        jobLog.setParams(scheduleJob.getParams());
        jobLog.setCreateTime(LocalDateTime.now());
        //任务开始执行时间
        StopWatch watch = new StopWatch();
        watch.start(scheduleJob.getBeanName());
        try {
            //执行任务
            logger.debug("任务准备执行，任务ID：" + scheduleJob.getJobId());
            //Object target = ApplicationContextTools.getBean(scheduleJob.getBeanName());
            //Method method = target.getClass().getDeclaredMethod("run", String.class);
            //method.invoke(target, scheduleJob.getParams());
            this.executeJob(jobContext);
            //任务执行总时长
            watch.stop();
            int times = (int) watch.getTotalTimeSeconds();
            jobLog.setTimes(times);
            //任务状态    0：成功    1：失败
            jobLog.setStatus(0);
            logger.debug("任务执行完毕，任务ID：" + scheduleJob.getJobId() + "  总共耗时：" + times + "毫秒");
        } catch (Exception e) {
            logger.error("任务执行失败，任务ID：" + scheduleJob.getJobId(), e);
            //任务执行总时长
            watch.stop();
            int times = (int) watch.getTotalTimeSeconds();
            jobLog.setTimes(times);

            //任务状态    0：成功    1：失败
            jobLog.setStatus(1);
            jobLog.setError(StringUtils.substring(e.toString(), 0, 2000));
        }finally {
            scheduleJobLogService.save(jobLog);
        }
    }

    protected abstract void executeJob(JobExecutionContext jobContext) throws Exception;
}
