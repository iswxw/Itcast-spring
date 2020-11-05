package com.wxw.controller;


import com.wxw.common.result.PageResult;
import com.wxw.domain.ScheduleJobLog;
import com.wxw.service.IScheduleJobLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 定时任务日志 前端控制器
 * </p>
 *
 * @author WXW
 * @since 2020-11-04
 */
@RestController
@RequestMapping("/schedule-job-log")
public class ScheduleJobLogController {

    @Resource
    private IScheduleJobLogService scheduleJobLogService;

    /**
     * 定时任务日志列表
     */
    @RequestMapping("/list")
    public ResponseEntity<PageResult<ScheduleJobLog>> list(@RequestParam Map<String, Object> params){
        PageResult<ScheduleJobLog> pageResult = scheduleJobLogService.queryScheduleLogByPage(params);
        if (pageResult == null || CollectionUtils.isEmpty(pageResult.getItems())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pageResult);
    }

}

