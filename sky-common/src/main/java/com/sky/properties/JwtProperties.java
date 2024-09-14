package com.sky.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "sky.jwt")
@Data
public class JwtProperties {

    /**
     * 管理端员工生成jwt令牌相关配置
     */
    private String adminSecretKey;
    private long adminTtl;
    private String adminTokenName;
    private Boolean isHidden;
    /**
     * 用户端微信用户生成jwt令牌相关配置
     */
    private String userSecretKey;
    private long userTtl;
    private String userTokenName;

    public JwtProperties() {}
    
    public void setAdminSecretKey(String secretKey) {
    	this.adminSecretKey = secretKey;
    }
    
    public void setAdminTtl(Long adminTtl) {
    	this.adminTtl = adminTtl;
    }
    
    public void setAdminTokenName(String adminTokenName) {
    	this.adminTokenName = adminTokenName;
    }
    
    public String getAdminSecretKey() {
    	return this.adminSecretKey;
    }
    
    public long getAdminTtl() {
    	return this.adminTtl;
    }
    
    public String getAdminTokenName() {
    	return this.adminTokenName;
    }
    
    public void setUserSecretKey(String secretKey) {
    	this.userSecretKey = secretKey;
    }
    
    public void setUserTtl(Long userTtl) {
    	this.userTtl = userTtl;
    }
    
    public void setUserTokenName(String userTokenName) {
    	this.userTokenName = userTokenName;
    }
    
    public String getUserSecretKey() {
    	return this.userSecretKey;
    }
    
    public long getUserTtl() {
    	return this.userTtl;
    }
    
    public String getUserTokenName() {
    	return this.userTokenName;
    }
}
