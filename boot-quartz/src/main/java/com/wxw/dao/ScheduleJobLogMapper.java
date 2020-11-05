package com.wxw.dao;

import com.wxw.domain.ScheduleJobLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 定时任务日志 Mapper 接口
 * </p>
 *
 * @author WXW
 * @since 2020-11-04
 */
@Mapper
public interface ScheduleJobLogMapper extends BaseMapper<ScheduleJobLog> {

}
