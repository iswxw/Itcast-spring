package com.wxw.common.excel;


import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSONArray;
import com.wxw.domain.SchedualJob;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @ Author ：wxw.
 * @ Date ： 8:48 2020/8/27
 * @ Description：Excel测试
 * @ Version:   v_0.0.1
 */
@SpringBootTest
public class TestExport {

    @Test
    public void testExport(){
        ExcelReader reader = ExcelUtil.getReader("D:\\Project\\wxw2020\\Itcast-spring\\boot-file\\doc\\excel\\task-job - 副本.xlsx");
        List<SchedualJob> readAll = reader.readAll(SchedualJob.class);
        System.out.println("readAll.size() = " + readAll.size());
        for (SchedualJob schedualJob : readAll) {
            System.out.println("schedualJob = " + schedualJob);
        }

        // 转换为JSON
        Object toJSON = JSONArray.toJSON(readAll);
        System.out.println("toJSON = " + toJSON);
        List<SchedualJob> schedualJobs = JSONArray.parseArray(toJSON.toString(), SchedualJob.class);
        System.out.println("schedualJobs = " + schedualJobs);
    }
}
