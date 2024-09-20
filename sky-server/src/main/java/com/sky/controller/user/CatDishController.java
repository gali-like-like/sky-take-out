package com.sky.controller.user;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Api(tags = {"菜品浏览接口"})
@RestController
public class CatDishController {

    @ApiOperation(value = "根据分类id查看菜品")
    @GetMapping("/user/dish/list")
    public Result getDishListByCategoryId(@RequestParam int categoryId) {
        return Result.success();
    }
}
