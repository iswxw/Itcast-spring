package com.wxw.dao;

import com.wxw.domain.ScheduleJob;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 定时任务 Mapper 接口
 * </p>
 *
 * @author WXW
 * @since 2020-11-04
 */
@Mapper
public interface ScheduleJobMapper extends BaseMapper<ScheduleJob> {

    /**
     * 批量更新状态
     */
    int updateBatch(Map<String, Object> map);

    /**
     * 查询任务集合
     * @return
     */
    List<ScheduleJob> list();

}
