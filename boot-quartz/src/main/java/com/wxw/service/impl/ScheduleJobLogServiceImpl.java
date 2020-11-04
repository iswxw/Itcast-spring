package com.wxw.service.impl;

import com.wxw.domain.ScheduleJobLog;
import com.wxw.dao.ScheduleJobLogMapper;
import com.wxw.service.IScheduleJobLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 定时任务日志 服务实现类
 * </p>
 *
 * @author WXW
 * @since 2020-11-04
 */
@Service
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogMapper, ScheduleJobLog> implements IScheduleJobLogService {

    @Autowired
    private IScheduleJobLogService iScheduleJobLogService;
}
