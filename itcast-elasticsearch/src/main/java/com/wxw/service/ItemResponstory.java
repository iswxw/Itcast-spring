package com.wxw.service;


import com.wxw.domain.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @Author: wxw
 * @create: 2020-04-10-23:16
 *  支持自定义方法
 */
public interface ItemResponstory extends ElasticsearchRepository<Item,Long> {

    /**
     * 根据标题查询
     *
     * @param title
     * @return
     */
    List<Item> findByTitle(String title);

    /**
     * 根据价格区间查询
     * @param price1
     * @param price2
     * @return
     */
    List<Item> findByPriceBetween(double price1, double price2);
}
