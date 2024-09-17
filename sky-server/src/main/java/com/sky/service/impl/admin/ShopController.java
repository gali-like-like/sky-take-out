package com.sky.service.impl.admin;

import com.sky.orther.GlobalMapSingleton;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController("adminShopController")
@RequestMapping("/shop")
@Api(tags = "店铺相关接口")
@Slf4j
public class ShopController {

    public static final String KEY = "SHOP_STATUS";


    /**
     * 获取店铺的营业状态
     * @return
     */
    @GetMapping("/status")
    @ApiOperation("获取店铺的营业状态")
    public Result<Integer> getStatus(){
        // 获取全局单例
        GlobalMapSingleton globalMapSingleton = GlobalMapSingleton.getInstance();
        Integer status = (Integer) globalMapSingleton.getData(KEY);
        log.info("获取到店铺的营业状态为：{}",status == 1 ? "营业中" : "打烊中");
        return Result.success(status);
    }

    /**
     * 设置店铺的营业状态
     * @param status
     * @return
     */
    @PutMapping("/{status}")
    @ApiOperation("设置店铺的营业状态")
    public Result setStatus(@PathVariable Integer status){
        log.info("设置店铺的营业状态为：{}",status == 1 ? "营业中" : "打烊中");
        GlobalMapSingleton globalMapSingleton = GlobalMapSingleton.getInstance();
        globalMapSingleton.putData(KEY,status);
        return Result.success();
    }


}
