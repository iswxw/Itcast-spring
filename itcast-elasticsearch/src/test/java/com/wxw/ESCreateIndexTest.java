package com.wxw;

import com.wxw.domain.Item;
import com.wxw.service.ItemResponstory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author: wxw
 * @create: 2020-04-10-22:51
 */
@SpringBootTest
public class ESCreateIndexTest {

    @Autowired
    private ElasticsearchTemplate  elasticsearchTemplate;

    @Autowired
    private ItemResponstory itemResponstory;

    /**
     * 创建索引库和映射配置
     * 查看索引库：get /item
     */
    @Test
    public void CreateIndexTest(){
        // 创建索引，会根据Item类的@Document注解信息来创建
        elasticsearchTemplate.createIndex(Item.class);
        // 配置映射，会根据Item类中的id、Field等字段来自动完成映射
        elasticsearchTemplate.putMapping(Item.class);
    }

    /**
     * 新增数据
     * get /item/_search
     */
    @Test
    public void addDataTest(){
        Item item = new Item(1L, "小米手机7", " 手机",
                "小米", 3499.00, "http://image.leyou.com/13123.jpg");
        itemResponstory.save(item);
    }

    /**
     * 批量新增数据
     * get /item/_search
     */
    @Test
    public void addDataListTest(){
        List<Item> list = new ArrayList<>();
        list.add(new Item(2L, "坚果手机R1", " 手机", "锤子", 3699.00, "http://image.leyou.com/123.jpg"));
        list.add(new Item(3L, "华为META10", " 手机", "华为", 4499.00, "http://image.leyou.com/3.jpg"));
        // 接收对象集合，实现批量新增
        itemResponstory.saveAll(list);
    }

    /**
     * 按照ID 查询
     */
    @Test
    public void testQuery(){
        Optional<Item> optional = this.itemResponstory.findById(1l);
        System.out.println(optional.get());
    }

    /**
     * 查询全部，并按照价格降序排序
     */
    @Test
    public void testFind(){

        Iterable<Item> items = this.itemResponstory.findAll(Sort.by(Sort.Direction.DESC, "price"));
        items.forEach(item-> System.out.println(item));
    }


    // 自定义查询方法
    @Test
    public void testfindByTitle(){
        List<Item> items = itemResponstory.findByTitle("小米");
        items.forEach(item-> System.out.println(item));
    }

    /**
     *  批量导入数据
     */
    @Test
    public void indexList() {
        List<Item> list = new ArrayList<>();
        list.add(new Item(1L, "小米手机7", "手机", "小米", 3299.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(2L, "坚果手机R1", "手机", "锤子", 3699.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(3L, "华为META10", "手机", "华为", 4499.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(4L, "小米Mix2S", "手机", "小米", 4299.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(5L, "荣耀V10", "手机", "华为", 2799.00, "http://image.leyou.com/13123.jpg"));
        // 接收对象集合，实现批量新增
        itemResponstory.saveAll(list);
    }




}
