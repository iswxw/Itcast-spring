package com.wxw.spring.controller;

import com.wxw.spring.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author: wxw
 * @create: 2020-03-24-0:49
 */
@RestController
public class TestController {

    @GetMapping("/user")
    public User testHttp(){
        User user = new User();
        user.setId(1L);
        user.setBirthday(new Date());
        user.setAge(12);
        user.setName("魏永杰");
        user.setCreated(new Date());
        return user;
    }
}
