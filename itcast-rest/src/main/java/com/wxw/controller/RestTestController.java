package com.wxw.controller;

import com.wxw.domain.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wxw
 * @create: 2020-03-24-10:09
 */
@RestController
public class RestTestController {

    @RequestMapping("/person")
    public Person testRestRemote(){
        Person person = new Person(1,"魏永杰",18,"测试Rest远程服务调用");
        return person;
    }
}
