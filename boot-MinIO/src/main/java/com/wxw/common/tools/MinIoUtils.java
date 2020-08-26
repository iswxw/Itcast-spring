package com.wxw.common.tools;

import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.PutObjectOptions;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import io.minio.messages.DeleteError;
import io.minio.messages.Item;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ Author ：wxw.
 * @ Date ： 15:46 2020/8/20
 * @ Description：使用MinIo进行文件 对象存储服务工具类
 * @ Version:   v_0.0.1
 */
@Component
public class MinIoUtils {


    private static final int DEFAULT_EXPIRY_TIME = 7 * 24 * 3600; // 7天

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

    /**
     * 删除存储桶
     * @param bucketName
     * @return
     */
    public boolean removeBucket(String bucketName) {
        boolean flag = bucketExists(bucketName);
        try {
            if (flag) {
                Iterable<Result<Item>> myObjects = listObjects(bucketName);
                for (Result<Item> result : myObjects) {
                    Item item = result.get();
                    // 有对象文件，则删除失败
                    if (item.size() > 0) {
                        return false;
                    }
                }
                minioClient.removeBucket(bucketName);
                flag = bucketExists(bucketName);
                if (!flag) return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 通过文件上传到对象
     * @param bucketName
     * @param objectName
     * @param fileName
     * @return
     */
    public boolean putObject(String bucketName, String objectName, String fileName) {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            try {
                minioClient.putObject(bucketName, objectName, fileName, null);
                ObjectStat statObject = statObject(bucketName, objectName);
                if (statObject != null && statObject.length() > 0) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;

    }

    /**
     * 通过InputStream上传对象
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @param stream     要上传的流
     */
    public boolean putObject(String bucketName, String objectName, InputStream stream) {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            try {
                minioClient.putObject(bucketName, objectName, stream, new PutObjectOptions(stream.available(), -1));
            } catch (Exception e) {
                e.printStackTrace();
            }
            ObjectStat statObject = statObject(bucketName, objectName);
            if (statObject != null && statObject.length() > 0) {
                return true;
            }
        }
        return false;
    }


    /**
     * 以流的形式获取一个文件对象
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     */
    public InputStream getObject(String bucketName, String objectName) {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            ObjectStat statObject = statObject(bucketName, objectName);
            if (statObject != null && statObject.length() > 0) {
                InputStream stream = null;
                try {
                    stream = minioClient.getObject(bucketName, objectName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return stream;
            }
        }
        return null;
    }

    /**
     * 以流的形式获取一个文件对象（断点下载）
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @param offset     起始字节的位置
     * @param length     要读取的长度 (可选，如果无值则代表读到文件结尾)
     */
    public InputStream getObject(String bucketName, String objectName, long offset, Long length) {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            ObjectStat statObject = statObject(bucketName, objectName);
            if (statObject != null && statObject.length() > 0) {
                InputStream stream = null;
                try {
                    stream = minioClient.getObject(bucketName, objectName, offset, length);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return stream;
            }
        }
        return null;
    }

    /**
     * 下载并将文件保存到本地
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @param fileName   File name
     * @return
     */
    public boolean getObject(String bucketName, String objectName, String fileName) {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            ObjectStat statObject = statObject(bucketName, objectName);
            if (statObject != null && statObject.length() > 0) {
                try {
                    minioClient.getObject(bucketName, objectName, fileName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 删除一个对象
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     */
    public boolean removeObject(String bucketName, String objectName) {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            try {
                minioClient.removeObject(bucketName, objectName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    /**
     * 删除指定桶的多个文件对象,返回删除错误的对象列表，全部删除成功，返回空列表
     *
     * @param bucketName  存储桶名称
     * @param objectNames 含有要删除的多个object名称的迭代器对象
     * @return
     */
    public List<String> removeObject(String bucketName, List<String> objectNames) {
        List<String> deleteErrorNames = new ArrayList<>();
        boolean flag = bucketExists(bucketName);
        if (flag) {
            Iterable<Result<DeleteError>> results = minioClient.removeObjects(bucketName, objectNames);
            for (Result<DeleteError> result : results) {
                DeleteError error = null;
                try {
                    error = result.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                deleteErrorNames.add(error.objectName());
            }
        }
        return deleteErrorNames;
    }


    /**
     * 生成一个给HTTP GET请求用的presigned URL。
     * 浏览器/移动端的客户端可以用这个URL进行下载，即使其所在的存储桶是私有的。这个presigned URL可以设置一个失效时间，默认值是7天。
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @param expires    失效时间（以秒为单位），默认是7天，不得大于七天
     */
    public String presignedGetObject(String bucketName, String objectName, Integer expires) throws InvalidExpiresRangeException {
        boolean flag = bucketExists(bucketName);
        String url = "";
        if (flag) {
            if (expires < 1 || expires > DEFAULT_EXPIRY_TIME) {
                throw new InvalidExpiresRangeException(expires, "expires must be in range of 1 to " + DEFAULT_EXPIRY_TIME);
            }
            try {
                url = minioClient.presignedGetObject(bucketName, objectName, expires);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return url;
    }

    /**
     * 文件访问路径
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @return
     */
    public String getObjectUrl(String bucketName, String objectName)  {
        boolean flag = bucketExists(bucketName);
        String url = "";
        if (flag) {
            try {
                url = minioClient.getObjectUrl(bucketName, objectName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return url;
    }

    /**
     * 获取对象的元数据
     * @param bucketName
     * @param objectName
     * @return
     */
    private ObjectStat statObject(String bucketName, String objectName) {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            ObjectStat statObject = null;
            try {
                statObject = minioClient.statObject(bucketName, objectName);
            }  catch (Exception e) {
                e.printStackTrace();
            }
            return statObject;
        }
        return null;
    }


    /**
     * 列出所有存储桶的名称
     */
    public List<String> listBucketNames(){
        List<Bucket> bucketList = listBuckets();
        List<String> bucketListName = new ArrayList<>();
        for (Bucket bucket : bucketList) {
            bucketListName.add(bucket.name());
        }
        return bucketListName;
    }

    /**
     * 列出所有桶
     * @return
     */
    public List<Bucket> listBuckets(){
        try {
            return minioClient.listBuckets();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }


    /**
     * 列出存储桶中的所有对象
     * @param bucketName
     * @return
     */
    public Iterable<Result<Item>> listObjects(String bucketName) {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            try {
                return minioClient.listObjects(bucketName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }



}
