package com.wxw;

import com.wxw.domain.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

/**
 * @Author: wxw
 * @create: 2020-04-10-22:51
 */
@SpringBootTest
public class ESCreateIndexTest {

    @Autowired
    private ElasticsearchTemplate  elasticsearchTemplate;

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

}
