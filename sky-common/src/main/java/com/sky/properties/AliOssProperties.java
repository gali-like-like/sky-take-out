package com.sky.properties;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
//@ConfigurationProperties(prefix = "sky.alioss")//添加配置文件属性
@Data
public class AliOssProperties {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

    public AliOssProperties() {
    }

    public AliOssProperties(String endPoint, String accessKeyId, String accessKeySecret, String bucketName) {
        this.endpoint = endPoint;
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        this.bucketName = bucketName;
    }

    public String getEndPoint() {
        return this.endpoint;
    }

    public void setEndPoint(String endPoint) {
        this.endpoint = endPoint;
    }

    public String getAccessKeyId() {
        return this.accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return this.accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
