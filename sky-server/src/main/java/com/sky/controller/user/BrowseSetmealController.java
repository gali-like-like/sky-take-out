package com.sky.controller.user;

import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/setmeal")
@Api(tags = "C端-套餐浏览接口")
@RequiredArgsConstructor
public class BrowseSetmealController {

    private final SetmealService setmealService;

    // 根据分类ID获取套餐列表
    @GetMapping("list")
    @ApiOperation(value = "根据分类ID获取套餐列表", notes = "根据分类ID获取套餐列表")
    public Result<List<Setmeal>> getSetmealList(@RequestParam Long categoryId) {
        List<Setmeal> setmeals = setmealService.getSetmealByCategoryId(categoryId);
        return Result.success(setmeals);
    }

    // 根据套餐id查询包含的菜品
    @GetMapping("/dish/{id}")
    @ApiOperation(value = "根据套餐id查询包含的菜品", notes = "根据套餐id查询包含的菜品")
    public Result<List<DishVO>> getMealList(@PathVariable("id") Long setmealId) {
        List<DishVO> dishList = setmealService.getMealListBySetmealId(setmealId);
        return Result.success(dishList);
    }

}
