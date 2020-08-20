package com.wxw;

import com.wxw.common.properties.MinIoProperties;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @ Author ：wxw.
 * @ Date ： 15:29 2020/8/20
 * @ Description：
 * @ Version:   v_0.0.1
 */
@SpringBootTest
public class GoalbleTest {

    @Resource
    private MinIoProperties minIoProperties;

    @Test
    public void testData1(){
        System.out.println("minIoProperties = " + minIoProperties.getEndpoint());
        System.out.println("minIoProperties = " + minIoProperties.getAccessKey());
        System.out.println("minIoProperties = " + minIoProperties.getSecretKey());
    }

}
