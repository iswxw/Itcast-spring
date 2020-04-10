package com.wxw.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "item",type = "docs", shards = 1, replicas = 0)
public class Item {

    @Id
    Long id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    String title; //标题

    @Field(type = FieldType.Keyword)
    String category;// 分类

    @Field(type = FieldType.Keyword)
    String brand; // 品牌

    @Field(type = FieldType.Double)
    Double price; // 价格

    @Field(index = false, type = FieldType.Keyword)
    String images; // 图片地址
}