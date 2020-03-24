package com.wxw.rest;

import com.wxw.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author: wxw
 * @create: 2020-03-24-9:37
 */
@SpringBootTest
public class RestTemplateTest {

   @Resource
   private RestTemplate restTemplate;

    @Test
    public void successTest(){
        System.out.println("测试是否成功！");
    }

    @Test
    public void httpGet() {
        Person person = this.restTemplate.getForObject("http://localhost:8080/person", Person.class);
        System.out.println(person);
    }



}
