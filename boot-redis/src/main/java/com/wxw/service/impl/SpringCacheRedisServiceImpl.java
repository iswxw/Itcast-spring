package com.wxw.service.impl;

import com.wxw.config.RedisCacheConfig;
import com.wxw.dao.SpringCacheRedisDao;
import com.wxw.domain.Address;
import com.wxw.domain.Person;
import com.wxw.service.SpringCacheRedisService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Date;

/**
 * @ Author ：wxw.
 * @ Date ： 12:56 2020/6/30
 * @ Description：缓存实现类‘
 * @ Version:   v_0.0.1
 */
@Service
public class SpringCacheRedisServiceImpl implements SpringCacheRedisService {


    @Resource
    private SpringCacheRedisDao springCacheRedisDao;

    @Override
    public String getString() {
        return springCacheRedisDao.getString();
    }

    /**
     * 将方法参数结果缓存起来，下一次方法执行参数相同时，将不执行方法，返回缓存中的结果
     * unless = "#result==null" 条件符合 不缓存
     *
     * @return
     */
    @Cacheable(value = RedisCacheConfig.REDIS_KEY_DATABASE, key = "'redis:person:'+#id", unless = "#result==null")
    @Override
    public Person getPerson(String id) {
        Address address = new Address(1L,"甘肃省","北京市","房山区","甘肃省 北京市 房山区");
        Person person = new Person(1L, "刘备", 18, new Date(), LocalDate.now(), address);
        return person;
    }




    /**
     * 该方法执行时会清空指定的缓存。一般使用在更新或删除方法上
     * @param id
     * @return
     */
    @CacheEvict(value = RedisCacheConfig.REDIS_KEY_DATABASE, key = "'redis:person:'+#id")
    @Override
    public String deletePerson(String id) {

        return "清除Redis 编号："+id+ "缓存成功！！！";
    }

    /**
     * 每次执行时都会把返回结果存入缓存中。一般使用在新增方法上
     * @param id
     * @return
     */
    @CachePut(value = RedisCacheConfig.REDIS_KEY_DATABASE, key = "'redis:person:'+#id")
    @Override
    public Person putPerson(String id) {
        Address address = new Address(1L,"甘肃省","北京市","房山区","甘肃省 北京市 房山区");
        Person person = new Person(1L, "刘备", 18, new Date(), LocalDate.now(), address);
        return person;
    }


}
