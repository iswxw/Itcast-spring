package com.wxw.controller.lock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author: wxw
 * @create: 2020-06-02-21:19
 */
@Slf4j
@RestController
public class LockController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/deduck_stock")
    public String deductStock() {
        String lockKey = "product_001::";
        String clientId = UUID.randomUUID().toString();
        try {
            Boolean result = this.stringRedisTemplate.opsForValue().setIfAbsent(lockKey, clientId, 10, TimeUnit.SECONDS);
            if (!result) {
                return "系统网络异常，请稍后重试！";
            }
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", realStock + "");
                log.info("扣减成功，剩余库存：", realStock);
            } else {
                log.info("扣减失败，库存不足");
            }

        } finally {
            if (clientId.equals(stringRedisTemplate.opsForValue().get(lockKey))) {
                this.stringRedisTemplate.delete(lockKey);
            }
        }
        return "erreo code";
    }

}
