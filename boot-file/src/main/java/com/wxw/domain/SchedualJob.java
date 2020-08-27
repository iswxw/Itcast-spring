package com.wxw.domain;

import lombok.Data;

/**
 * @ Author ：wxw.
 * @ Date ： 8:52 2020/8/27
 * @ Description：任务类
 * @ Version:   v_0.0.1
 */
@Data
public class SchedualJob {
    /*任务名称*/
    private String jobName;
    /*任务描述*/
    private String jobDescription;
    /*表达式*/
    private String jobCorn;
    /*触发器名称*/
    private String triggerName;
    /*触发器描述*/
    private String triggerDescription;
    /*任务状态*/
    private String status;
    /*分组*/
    private String scheduleGroup;
}
