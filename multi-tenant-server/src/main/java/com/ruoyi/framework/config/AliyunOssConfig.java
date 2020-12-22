package com.ruoyi.framework.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AliyunOssConfig {

    private static String endpoint;

    private static String accessKeyId;

    private static String accessKeySecret;

    private static String defaultBucketName;

    private static String endpointContent;

    private AliyunOssConfig() {
    }

    public static String getEndpoint() {
        return endpoint;
    }

    @Value("${aliyun.oss.endpoint}")
    private void setEndpoint(String endpoint) {
        AliyunOssConfig.endpoint = endpoint;
    }

    public static String getAccessKeyId() {
        return accessKeyId;
    }

    @Value("${aliyun.oss.accessKeyId}")
    private void setAccessKeyId(String accessKeyId) {
        AliyunOssConfig.accessKeyId = accessKeyId;
    }

    public static String getAccessKeySecret() {
        return accessKeySecret;
    }

    @Value("${aliyun.oss.accessKeySecret}")
    private void setAccessKeySecret(String accessKeySecret) {
        AliyunOssConfig.accessKeySecret = accessKeySecret;
    }

    public static String getDefaultBucketName() {
        return defaultBucketName;
    }

    @Value("${aliyun.oss.defaultBucketName}")
    private void setDefaultBucketName(String defaultBucketName) {
        AliyunOssConfig.defaultBucketName = defaultBucketName;
    }

    public static String getEndpointContent() {
        return endpointContent;
    }

    @Value("${aliyun.oss.endpointContent}")
    public void setEndpointContent(String endpointContent) {
        AliyunOssConfig.endpointContent = endpointContent;
    }
}
