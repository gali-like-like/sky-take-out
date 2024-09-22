package com.sky.service;

import com.sky.entity.SetmealDish;

import java.util.List;

/**
 * 套餐菜品关系(SetmealDish)表服务接口
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:42:02
 */
public interface SetmealDishService {
    //修改菜品套餐关系
    public void updateSetmealDish(SetmealDish setmealDish);

    //根据套餐id删除对应数据
    public void deleteSetmealDishById(Long setmealId);

    //新增套餐菜品关系
    public Integer insertSetmealDish(SetmealDish setmealDish);

    //根据套餐id查询菜品
    public List<SetmealDish> selectSetmealDishById(Long setmealId);
}
