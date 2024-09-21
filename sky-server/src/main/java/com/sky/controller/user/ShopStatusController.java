package com.sky.controller.user;


import com.sky.other.GlobalMapSingleton;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("user/shop")
@Api(tags = "商城状态")
public class ShopStatusController {

    public static final String KEY = "SHOP_STATUS";

    @GetMapping("status")
    public Result<Integer> getShopStatus() {
        Integer status = (Integer) GlobalMapSingleton.getInstance().getData(KEY);
        return Result.success(status);
    }
}
