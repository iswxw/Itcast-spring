package com.wxw.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.wxw.common.result.PageResult;
import com.wxw.domain.ScheduleJob;
import com.wxw.service.IScheduleJobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Wrapper;
import java.util.Map;

/**
 * <p>
 * 定时任务 前端控制器
 * </p>
 *
 * @author WXW
 * @since 2020-11-04
 */
@RestController
@RequestMapping("/schedule-job")
public class ScheduleJobController {

    @Resource
    private IScheduleJobService scheduleJobService;

    /**
     * 定时任务列表
     */
    @RequestMapping("/list")
    public ResponseEntity<PageResult<ScheduleJob>> list(@RequestParam Map<String, Object> params) {
        PageResult<ScheduleJob> pageResult = scheduleJobService.queryJobByPage(params);
        if (pageResult == null || CollectionUtils.isEmpty(pageResult.getItems())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pageResult);
    }

    /**
     * 根据jobId查询定时任务信息
     */
    @RequestMapping("/info/{jobId}")
    public ResponseEntity<ScheduleJob> info(@PathVariable("jobId") Long jobId) {
        ScheduleJob schedule = scheduleJobService.getById(jobId);
        if (schedule == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(schedule);
    }

    /**
     * 保存定时任务
     */
    @RequestMapping("/save")
    public ResponseEntity<Void> save(@RequestBody ScheduleJob scheduleJob) {
        scheduleJobService.saveJob(scheduleJob);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 修改定时任务
     */
    @RequestMapping("/update")
    public ResponseEntity<Void> update(@RequestBody ScheduleJob scheduleJob) {
        scheduleJobService.updateJob(scheduleJob);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * 删除定时任务
     */
    @RequestMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody Long[] jobIds){
        scheduleJobService.deleteBatch(jobIds);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 立即执行任务
     */
    @RequestMapping("/run")
    public ResponseEntity<Void> run(@RequestBody Long[] jobIds){
        scheduleJobService.run(jobIds);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 暂停定时任务
     */
    @RequestMapping("/pause")
    public ResponseEntity<Void> pause(@RequestBody Long[] jobIds){
        scheduleJobService.pause(jobIds);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 恢复定时任务
     */
    @RequestMapping("/resume")
    public ResponseEntity<Void>  resume(@RequestBody Long[] jobIds){
        scheduleJobService.resume(jobIds);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}

