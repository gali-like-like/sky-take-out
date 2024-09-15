package com.sky.properties;

import org.springframework.stereotype.Component;

@Component
//@ConfigurationProperties(prefix = "sky.wechat")//添加配置文件属性
public class WeChatProperties {

    private String appid; //小程序的appid
    private String secret; //小程序的秘钥
    private String mchid; //商户号
    private String mchSerialNo; //商户API证书的证书序列号
    private String privateKeyFilePath; //商户私钥文件
    private String apiV3Key; //证书解密的密钥
    private String weChatPayCertFilePath; //平台证书
    private String notifyUrl; //支付成功的回调地址
    private String refundNotifyUrl; //退款成功的回调地址

    public WeChatProperties(String appid,
                            String secret,
                            String mchid,
                            String mchSerialNo,
                            String privateKeyFilePath,
                            String apiV3Key,
                            String weChatPayCertFilePath,
                            String notifyUrl, String refundNotifyUrl) {
        this.appid = appid;
        this.secret = secret;
        this.mchid = mchid;
        this.mchSerialNo = mchSerialNo;
        this.privateKeyFilePath = privateKeyFilePath;
        this.apiV3Key = apiV3Key;
        this.weChatPayCertFilePath = weChatPayCertFilePath;
        this.notifyUrl = notifyUrl;
        this.refundNotifyUrl = refundNotifyUrl;
    }

    public WeChatProperties() {
    }

    public String getAppid() {
        return this.appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return this.secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getMchid() {
        return this.mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getMchSerialNo() {
        return this.mchSerialNo;
    }

    public void setMchSerialNo(String mchSerialNo) {
        this.mchSerialNo = mchSerialNo;
    }

    public String getPrivateKeyFilePath() {
        return this.privateKeyFilePath;
    }

    public void setPrivateKeyFilePath(String privateKeyFilePath) {
        this.privateKeyFilePath = privateKeyFilePath;
    }

    public String getApiV3Key() {
        return this.apiV3Key;
    }

    public void setApiV3Key(String apiV3Key) {
        this.apiV3Key = apiV3Key;
    }

    public String getWeChatPayCertFilePath() {
        return this.weChatPayCertFilePath;
    }

    public void setWeChatPayCertFilePath(String weChatPayCertFilePath) {
        this.weChatPayCertFilePath = weChatPayCertFilePath;
    }

    public String getNotifyUrl() {
        return this.notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getRefundNotifyUrl() {
        return this.refundNotifyUrl;
    }

    public void setRefundNotifyUrl(String refundNotifyUrl) {
        this.refundNotifyUrl = refundNotifyUrl;
    }

}
