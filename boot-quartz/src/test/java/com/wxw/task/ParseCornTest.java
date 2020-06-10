package com.wxw.task;

import lombok.extern.slf4j.Slf4j;
import org.quartz.TriggerUtils;
import org.quartz.impl.triggers.CronTriggerImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ Author ：wxw.
 * @ Date ： 14:55 2020/6/10
 * @ Description：反解析Corn表达式
 * @ Version:   v_0.0.1
 */
@Slf4j
public class ParseCornTest {

    public static void main(String[] args) {

        List<String> strings = null;
        try {
             strings = cronExpression2executeDates("0 0 1 * * ? *");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        strings.forEach(s -> System.out.println(s));

    }


    /**
     *  corn表单时解析为日志
     * @param cronExpression
     * @return
     * @throws ParseException
     */
    public static List<String> cronExpression2executeDates(String cronExpression) throws ParseException {
        List<String> resultList = new ArrayList<String>() ;
        CronTriggerImpl cronTriggerImpl = new CronTriggerImpl();
        cronTriggerImpl.setCronExpression(cronExpression);//这里写要准备猜测的cron表达式
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        calendar.add(Calendar.YEAR, 2);//把统计的区间段设置为从现在到2年后的今天（主要是为了方法通用考虑，如那些1个月跑一次的任务，如果时间段设置的较短就不足20条)
        List<Date> dates = TriggerUtils.computeFireTimesBetween(cronTriggerImpl, null, now, calendar.getTime());//这个是重点，一行代码搞定~~
        System.out.println(dates.size());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(int i =0;i < dates.size();i ++){
            if(i >19){//这个是提示的日期个数
                break;
            }
            resultList.add(dateFormat.format(dates.get(i)));
        }
        return resultList ;
    }
}
