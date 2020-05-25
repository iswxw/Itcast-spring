package com.wxw;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.wxw.dao")
@SpringBootApplication
public class QuartzMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(QuartzMain8001.class,args);
    }

}
