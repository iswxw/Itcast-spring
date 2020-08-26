package com.wxw.controller;

import com.wxw.common.tools.MinIoUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.UUID;

/**
 * @ Author ：wxw.
 * @ Date ： 9:23 2020/8/26
 * @ Description：文件管理控制器
 * @ Version:   v_0.0.1
 */
@RestController
public class FileController {

    @Resource
    private MinIoUtils minIoUtils;

    @PostMapping("/upload")
    public String MinIOUpload(MultipartFile file) {
        if (file.isEmpty() || file.getSize() == 0) {
            return "文件为空";
        }
        try {
            if (!minIoUtils.bucketExists("javakf")) {
                minIoUtils.makeBucket("javakf");
            }
            String fileName = file.getOriginalFilename();
            String newName = "image/" + UUID.randomUUID().toString().replaceAll("-", "")
                    + fileName.substring(fileName.lastIndexOf("."));

            InputStream inputStream = file.getInputStream();
            minIoUtils.putObject("javakf", newName, inputStream);
            inputStream.close();

            String url = minIoUtils.getObjectUrl("javakf", newName);
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败";
        }
    }

}
