package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sky.constant.MessageConstant;
import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.exception.LoginFailedException;
import com.sky.exception.UserNotFoundException;
import com.sky.mapper.UserMapper;
import com.sky.properties.JwtProperties;
import com.sky.properties.WeChatProperties;
import com.sky.service.UserService;
import com.sky.utils.HttpClientUtil;
import com.sky.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户信息(User)表服务实现类
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:42:01
 */
@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    public static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";
    @Autowired
    private WeChatProperties weChatProperties;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public HashMap<String,Object> wxLogin(String code) {
        String openid = getOpenId(code);
        Long userId = 0L;
        if(openid == null)
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        User user = userMapper.getUserByOpenId(openid);
        if(user == null) {
            //用户不存在就注册
            user = new User();
            user.setOpenid(openid);
            user.setCreateTime(LocalDateTime.now());
            userId = userMapper.insert(user);
        }
        userId = user.getId();
        HashMap<String,Object> hashMap = new HashMap<>();
        //自定义jwt的数据载荷
        hashMap.put("open_id",openid);
        hashMap.put("id",userId);
        hashMap.put("create_time",LocalDateTime.now());
        String jwt = JwtUtil.createJWT(jwtProperties.getUserSecretKey(),jwtProperties.getUserTtl(),hashMap);
        HashMap<String,Object> finallyResult = new HashMap<>();
        finallyResult.put("id",userId);
        finallyResult.put("openid",openid);
        finallyResult.put("token",jwt);
        return finallyResult;
    }

    private String getOpenId(String code) {
        Map map = new HashMap();
        map.put("appid",weChatProperties.getAppid());
        map.put("secert",weChatProperties.getSecret());
        map.put("js_code",code);
        map.put("grant_type","authorization_code");

        String json = HttpClientUtil.doGet(WX_LOGIN,map);
        log.info("微信登录返回结果:{}",json);

        JSONObject jsonObject = JSON.parseObject(json);
        String openId = jsonObject.getString("openid");
        log.info("微信用户的openid为:{}",openId);
        return openId;
    }





}
