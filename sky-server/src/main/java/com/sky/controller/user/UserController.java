package com.sky.controller.user;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.exception.LoginFailedException;
import com.sky.result.Result;
import com.sky.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Objects;

@RestController("/user/user")
@Api(tags = {"用户接口"})
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * login 用户登录接口
     * code :微信授权码
     *
     * Result : 响应结果
     * */
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody String code) throws LoginFailedException {
        HashMap<String,Object> result = userService.wxLogin(code);
        return Result.success(result);
    }

    @ApiOperation(value = "用户退出")
    @PostMapping("/loginout")
    public Result loginout() {
        log.info("用户退出了");
        return Result.success();
    }

}
