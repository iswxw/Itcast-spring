package com.wxw.service;

import com.wxw.common.result.PageResult;
import com.wxw.domain.ScheduleJobLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 定时任务日志 服务类
 * </p>
 *
 * @author WXW
 * @since 2020-11-04
 */
public interface IScheduleJobLogService extends IService<ScheduleJobLog> {

    /**
     * 定时任务日志列表
     * @param params
     * @return
     */
    PageResult<ScheduleJobLog> queryScheduleLogByPage(Map<String, Object> params);
}
