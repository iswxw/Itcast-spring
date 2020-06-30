package com.wxw.controller.web;

import com.wxw.domain.Person;
import com.wxw.service.SpringCacheRedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ Author ：wxw.
 * @ Date ： 13:01 2020/6/30
 * @ Description：缓存控制层
 * @ Version:   v_0.0.1
 */
@RestController
public class SpringCacheRedisController {

    @Resource
    private SpringCacheRedisService springCacheRedisService;


    @GetMapping("cache")
    public String getString(){
        return springCacheRedisService.getString();
    }

    /**
     * 删除
     * @return
     */
    @GetMapping("person/delete/{id}")
    public String deletePerson(@PathVariable("id")String id){
        return springCacheRedisService.deletePerson(id);
    }

    /**
     * 添加缓存
     * @param id
     * @return
     */
    @GetMapping("person/get/{id}")
    public Person getPerson(@PathVariable("id")String id){
        return springCacheRedisService.getPerson(id);
    }


    /**
     * Put 修改
     * @return
     */
    @GetMapping("person/put/{id}")
    public Person putPerson(@PathVariable("id")String id){
        return springCacheRedisService.putPerson(id);
    }
}
