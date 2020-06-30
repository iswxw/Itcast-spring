package com.wxw.controller.web;

import com.wxw.service.MallRedisService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Redis测试Controller
 * Created by macro on 2020/3/3.
 */

@Controller
@RequestMapping("/redis")
public class RedisController {


    @Resource
    private MallRedisService mallRedisService;





}