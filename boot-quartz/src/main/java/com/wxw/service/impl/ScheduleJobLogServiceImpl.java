package com.wxw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxw.common.result.PageResult;
import com.wxw.domain.ScheduleJob;
import com.wxw.domain.ScheduleJobLog;
import com.wxw.dao.ScheduleJobLogMapper;
import com.wxw.service.IScheduleJobLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 定时任务日志 服务实现类
 * </p>
 *
 * @author WXW
 * @since 2020-11-04
 */
@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogMapper, ScheduleJobLog> implements IScheduleJobLogService {

    @Resource
    private IScheduleJobLogService scheduleJobLogService;

    @Override
    public PageResult<ScheduleJobLog> queryScheduleLogByPage(Map<String, Object> params) {
        String jobId = (String) params.get("jobId");
        IPage<ScheduleJobLog> page = new Page<>();
        IPage<ScheduleJobLog> pages = this.page(page, new QueryWrapper<ScheduleJobLog>().like(StringUtils.isNotBlank(jobId), "job_id", jobId));
        return new PageResult<>(page.getTotal(), page.getRecords());
    }
}
