package com.wxw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxw.common.Constant;
import com.wxw.common.result.PageResult;
import com.wxw.common.tools.ScheduleUtils;
import com.wxw.domain.ScheduleJob;
import com.wxw.dao.ScheduleJobMapper;
import com.wxw.service.IScheduleJobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 定时任务 服务实现类
 * </p>
 *
 * @author WXW
 * @since 2020-11-04
 */
@Service
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobMapper, ScheduleJob> implements IScheduleJobService {

    @Resource
    private Scheduler scheduler;

    @Resource
    private ScheduleJobMapper scheduleJobMapper;
    /**
     * 项目启动时，初始化定时器
     */
    @PostConstruct
    public void init(){
        List<ScheduleJob> scheduleJobList = this.list();
        for(ScheduleJob scheduleJob : scheduleJobList){
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
            //如果不存在，则创建
            if(cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            }else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        }
    }

    @Override
    public PageResult<ScheduleJob> queryJobByPage(Map<String, Object> params) {
        String beanName = (String)params.get("beanName");
        IPage<ScheduleJob> page = new Page<>();
        IPage<ScheduleJob> pages = this.page(page, new QueryWrapper<ScheduleJob>().like(StringUtils.isNotBlank(beanName),"bean_name", beanName));
        return  new PageResult<>(page.getTotal(),page.getRecords());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveJob(ScheduleJob scheduleJob) {
        scheduleJob.setCreateTime(LocalDateTime.now());
        scheduleJob.setStatus(Constant.ScheduleStatus.NORMAL.getValue());
        this.save(scheduleJob);
        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateJob(ScheduleJob scheduleJob) {
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
        this.updateById(scheduleJob);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long... jobIds) {
        for(Long jobId : jobIds){
            ScheduleUtils.deleteScheduleJob(scheduler, jobId);
        }
        //删除数据
        this.removeByIds(Arrays.asList(jobIds));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void run(Long... jobIds) {
        for(Long jobId : jobIds){
            ScheduleUtils.run(scheduler, this.getById(jobId));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pause(Long... jobIds) {
        for(Long jobId : jobIds){
            ScheduleUtils.pauseJob(scheduler, jobId);
        }
       // updateBatch(jobIds, Constant.ScheduleStatus.PAUSE.getValue());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resume(Long... jobIds) {
        for(Long jobId : jobIds){
            ScheduleUtils.resumeJob(scheduler, jobId);
        }
      //  updateBatch(jobIds, Constant.ScheduleStatus.NORMAL.getValue());
    }

}
