package com.sky.controller.user;

import com.sky.entity.User;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user/user")
@Api(tags = {"用户接口"})
public class UserController {
    /**
     * login 用户登录接口
     * code :微信授权码
     *
     * Result : 响应结果
     * */
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody String code) {
        return Result.success();
    }

    @ApiOperation(value = "用户退出")
    @PostMapping("/loginout")
    public Result loginout() {
        return Result.success();
    }

}
