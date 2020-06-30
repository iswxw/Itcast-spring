package com.wxw.service;

import com.wxw.domain.Person;

/**
 * @ Author ：wxw.
 * @ Date ： 12:55 2020/6/30
 * @ Description：Spring Cache 结合redis实现缓存
 * @ Version:   v_0.0.1
 */
public interface SpringCacheRedisService {


    /**
     * 获取用户名
     * @return
     */
    String getString();

    /**
     * 获取用户信息
     * @return
     */
    Person getPerson(String id);

    /**
     * 删除个人信息
     * @return
     */
    String deletePerson(String id);


    /**
     * 修改个人信息
     * @param id
     * @return
     */
    Person putPerson(String id);
}
