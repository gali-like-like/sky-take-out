package com.sky.properties;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
//@ConfigurationProperties(prefix = "sky.alioss")//添加配置文件属性
@Data
public class AliOssProperties {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

    public AliOssProperties() {}
    
    public AliOssProperties(String endPoint,String accessKeyId,String accessKeySecret,String bucketName) {
    	this.endpoint = endPoint;
    	this.accessKeyId = accessKeyId;
    	this.accessKeySecret = accessKeySecret;
    	this.bucketName = bucketName;
    }
    
    public void setEndPoint(String endPoint) {
    	this.endpoint = endPoint;
    }
    
    public String getEndPoint() {
    	return this.endpoint;
    }
    
    public void setAccessKeyId(String accessKeyId) {
    	this.accessKeyId = accessKeyId;
    }
    
    public String getAccessKeyId() {
    	return this.accessKeyId;
    }
    
    public void setAccessKeySecret(String accessKeySecret) {
    	this.accessKeySecret = accessKeySecret;
    }
    
    public String getAccessKeySecret() {
    	return this.accessKeySecret;
    }
    
    public void setBucketName(String bucketName) {
    	this.bucketName = bucketName;
    }
    
    public String getBucketName() {
    	return this.bucketName;
    }
}
