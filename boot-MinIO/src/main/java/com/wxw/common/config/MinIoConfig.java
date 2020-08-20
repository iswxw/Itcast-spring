package com.wxw.common.config;

import com.wxw.common.properties.MinIoProperties;
import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ Author ：wxw.
 * @ Date ： 15:53 2020/8/20
 * @ Description：文件存储服务配置类
 * @ Version:   v_0.0.1
 */
@Component
public class MinIoConfig {

    @Resource
    private MinIoProperties minIoProperties;

    @Bean
     public MinioClient minioClient(){
         MinioClient instance = null;
         try {
             instance = new MinioClient(minIoProperties.getEndpoint(),minIoProperties.getAccessKey(),minIoProperties.getSecretKey());
         } catch (InvalidEndpointException e) {
             e.printStackTrace();
         } catch (InvalidPortException e) {
             e.printStackTrace();
         }
         return instance;
     }
}
