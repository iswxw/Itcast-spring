package com.wxw.service;

import com.wxw.common.result.PageResult;
import com.wxw.domain.ScheduleJob;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 定时任务 服务类
 * </p>
 *
 * @author WXW
 * @since 2020-11-04
 */
public interface IScheduleJobService extends IService<ScheduleJob> {

    /**
     * 定时任务列表
     * @param params
     * @return
     */
    PageResult<ScheduleJob> queryJobByPage(Map<String, Object> params);

    /**
     * 保存定时任务
     * @param scheduleJob
     */
    void saveJob(ScheduleJob scheduleJob);

    /**
     * 批量删除任务
     * @param jobIds
     */
    void deleteBatch(List<Long> jobIds);

    /**
     * 更新定时任务
     * @param scheduleJob
     */
    void updateJob(ScheduleJob scheduleJob);

    /**
     * 立即执行定时任务
     * @param jobIds
     */
    void run(List<Long> jobIds);

    /**
     * 暂停定时任务
     * @param jobIds
     */
    void pause(List<Long> jobIds);

    /**
     * 恢复定时任务
     * @param jobIds
     */
    void resume(List<Long> jobIds);

    /**
     * 批量更新定时任务状态
     */
    int updateBatch(List<Long> jobIds, int status);
}
