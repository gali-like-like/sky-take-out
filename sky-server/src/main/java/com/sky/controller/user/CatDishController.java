package com.sky.controller.user;

import com.sky.constant.MessageConstant;
import com.sky.entity.Dish;
import com.sky.mapper.DishMapper;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@Api(tags = {"菜品浏览接口"})
@RestController
public class CatDishController {

    @Autowired
    private DishMapper dishMapper;

    @ApiOperation(value = "根据分类id查看菜品")
    @GetMapping("/user/dish/list")
    public Result getDishListByCategoryId(@ApiParam(required = true,name = "categoryId") @RequestParam Long categoryId) {
        List<Dish> dishes = dishMapper.queryByCategoryId(categoryId);
        return Result.success(dishes);
    }
}
