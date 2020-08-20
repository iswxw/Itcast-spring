package com.wxw.common.tools;

import io.minio.MinioClient;
import io.minio.errors.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @ Author ：wxw.
 * @ Date ： 15:46 2020/8/20
 * @ Description：使用MinIo进行文件 对象存储服务工具类
 * @ Version:   v_0.0.1
 */
@Component
public class MinIoUtils {

    @Resource
    private MinioClient minioClient;

    /**
     * 判断bucket是否存在
     * @param bucketName
     * @return
     */
    public boolean bucketExists(String bucketName){
        try {
            return minioClient.bucketExists(bucketName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 创建bucket
     * @param bucketName
     */
    public void makeBucket(String bucketName) {
        try {
            boolean isExists = minioClient.bucketExists(bucketName);
            if (!isExists) {
                minioClient.makeBucket(bucketName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
