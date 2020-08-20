package com.wxw.common.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 *  指定具体的配置文件
 *  @PropertySource(value = {"classpath:minio.properties"})
 */
@Data
@PropertySource(value = {"classpath:minio.properties"})
@Component
@ConfigurationProperties(prefix = "min.io")
public class MinIoProperties {
    private String endpoint;
    private String accessKey;
    private String secretKey;
}