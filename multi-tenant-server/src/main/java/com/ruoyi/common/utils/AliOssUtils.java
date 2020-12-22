package com.ruoyi.common.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.ruoyi.framework.config.AliyunOssConfig;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class AliOssUtils {

    /**
     * 懒汉式单例，此处初始化时非线程安全，考虑到后台用户量，暂不优化
     */
    private static OSSClient OSS_CLIENT = null;

    private static OSSClient getOSSClient() {
        if (null == OSS_CLIENT) {
            OSS_CLIENT = new OSSClient(AliyunOssConfig.getEndpoint(), AliyunOssConfig.getAccessKeyId(), AliyunOssConfig.getAccessKeySecret());
        }
        return OSS_CLIENT;
    }

    public static void uploadFile(MultipartFile multipartFile, String fileName) {
        OSSClient ossClient = getOSSClient();
        try {
            if (!getOSSClient().doesBucketExist(AliyunOssConfig.getDefaultBucketName())) {
                ossClient.createBucket(AliyunOssConfig.getDefaultBucketName());
            }
            ossClient.putObject(AliyunOssConfig.getDefaultBucketName(), fileName,
                    multipartFile.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//      ossClient.shutdown();
        }
    }


    public static void download(String fileName) {
        OSSClient ossClient = getOSSClient();
        try {
            // 下载文件。详细请参看“SDK手册 > Java-SDK > 下载文件”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/download_object.html?spm=5176.docoss/sdk/java-sdk/manage_object
            OSSObject ossObject = ossClient.getObject(AliyunOssConfig.getDefaultBucketName(), fileName);
            InputStream inputStream = ossObject.getObjectContent();
            StringBuilder objectContent = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                objectContent.append(line);
            }

            inputStream.close();
            System.out.println("Object：" + fileName + "的内容是：" + objectContent);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//      ossClient.shutdown();
        }
    }

    public static void deleteFile(String fileName) {

        OSSClient ossClient = getOSSClient();
        try {
            ossClient.deleteObject(AliyunOssConfig.getDefaultBucketName(), fileName);
        } catch (Exception e) {

        } finally {
//      ossClient.shutdown();
        }
    }

    public static void listAllFiles() {
        OSSClient ossClient = getOSSClient();
        try {
            ObjectListing objectListing = ossClient.listObjects(AliyunOssConfig.getDefaultBucketName());
            List<OSSObjectSummary> objectSummary = objectListing.getObjectSummaries();
            System.out.println("您有以下Object：");
            for (OSSObjectSummary object : objectSummary) {
                System.out.println("\t" + object.getKey());
            }
        } catch (Exception e) {

        } finally {
//      ossClient.shutdown();
        }
    }
}
