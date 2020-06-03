package com.wxw.controller.web;

import com.wxw.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @ Author ：wxw.
 * @ Date ： 16:58 2020/6/3
 * @ Description：结合位图进行误差测试
 * @ Version:   v_0.0.1
 */
@Slf4j
@RestController
public class HyperLogController {

    /**
     *  https://juejin.im/post/5e148a6ee51d4540ef70a9dd#heading-0
     * 基于SpringBoot的进行 误差测试，初始化5个HyperLogLog，每个随机添加10000个元素，然后调用pfcount查看具体误差
     */

    @Resource
    private RedisService redisService;

    /**
     * http://localhost:7001/init
     * @return
     */
    @GetMapping("/init")
    public String init(){
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(()->{
                String threadName = Thread.currentThread().getName();
                Random random = new Random();
                int begin = random.nextInt(100)*10000;
                int end = begin + 10000;
                for (int j=begin;j<end;j++){
                    this.redisService.pfAdd("hll:"+threadName,j+"");
                }
                System.out.printf("线程【%s】完成数据初始化，区间[%d, %d)\n", threadName, begin, end);
            },i+"");
            thread.start();
        }
        return "Success 线程初始化完成！";
    }

    /**
     * http://localhost:7001/count
     * @return
     */
    @GetMapping("/count")
    public String count(){
        long a = redisService.pfCount("hll:0");
        long b = redisService.pfCount("hll:1");
        long c = redisService.pfCount("hll:2");
        long d = redisService.pfCount("hll:3");
        long e = redisService.pfCount("hll:4");
        System.out.printf("hll:0 -> count: %d, rate: %f\n", a, (10000 - a) * 1.00 / 100);
        System.out.printf("hll:1 -> count: %d, rate: %f\n", b, (10000 - b) * 1.00 / 100);
        System.out.printf("hll:2 -> count: %d, rate: %f\n", c, (10000 - c) * 1.00 / 100);
        System.out.printf("hll:3 -> count: %d, rate: %f\n", d, (10000 - d) * 1.00 / 100);
        System.out.printf("hll:4 -> count: %d, rate: %f\n", e, (10000 - e) * 1.00 / 100);
        return "统计误差结果完成";
    }

    /**
     * 实战：
     *  比如要统计文章的热度和有效用户点击数。可以通过Reis的计数器来统计热度，每次请就执行incr指令。通过HyperLogLog来统计有效用户数。
     * 思路：
     *  通过AOP和自定义注解来对需要统计的文章进行统计：
     *
     * 在需要统计的文章接口上加上注解
     *   设置自定义注解值为HyperLogLog对应的key
     *   将AOP的切入点设为自定义注解
     *   AOP中获取注解值
     *   AOP中通过token或者cookie判断用户信息
     *   累计热度和用户量
     */



}
