package com.wxw.tools;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.util.Date;

/**
 * @ Author ：wxw.
 * @ Date ： 18:11 2020/5/28
 * @ Description：日期工具类
 * @ Version:   v_0.0.1$
 */
public class ToolsDate {


    /**
     *  日期格式化为  yyyy-MM-dd
     */
    public static String getYyyyMmDd(Date date){
        return DateFormatUtils.format(date,"yyyy-MM-dd");
    }
}
