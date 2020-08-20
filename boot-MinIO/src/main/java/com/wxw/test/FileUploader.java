package com.wxw.test;

import io.minio.MinioClient;
import io.minio.errors.MinioException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @ Author ：wxw.
 * @ Date ： 15:00 2020/8/20
 * @ Description：文件上传
 * @ Version:   v_0.0.1
 */
public class FileUploader {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, InvalidKeyException, XmlPullParserException {
        try {
            // 使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
            MinioClient minioClient = new MinioClient("https://play.min.io", "Q3AM3UQ867SPQQA43P2F", "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG");

            // 检查存储桶是否已经存在
            boolean isExist = minioClient.bucketExists("asiatrip");
            if(isExist) {
                System.out.println("Bucket already exists.");
            } else {
                // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
                minioClient.makeBucket("asiatrip");
            }

            // 使用putObject上传一个文件到存储桶中。
            minioClient.putObject("asiatrip","asiaphotos.zip", "/home/user/Photos/asiaphotos.zip",null);
            System.out.println("/home/user/Photos/asiaphotos.zip is successfully uploaded as asiaphotos.zip to `asiatrip` bucket.");
        } catch(MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }
}