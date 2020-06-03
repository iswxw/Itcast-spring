package com.wxw.controller.redission;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: wxw
 * @create: 2020-06-02-21:59
 */
@Slf4j
@RestController
public class RedissionController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private Redisson redisson;

    @GetMapping("/redission_stock")
    public String deductStock(){
        String lockKey = "product_001::";
        RLock redissonLock = redisson.getLock(lockKey);
        try {
            redissonLock.lock();
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", realStock + "");
                log.info("扣减成功，剩余库存：{}", realStock);
            } else {
                log.info("扣减失败，库存不足");
            }
        }finally {
            redissonLock.unlock();
        }
        return "系统网络异常，请稍后重试！";
    }
}
