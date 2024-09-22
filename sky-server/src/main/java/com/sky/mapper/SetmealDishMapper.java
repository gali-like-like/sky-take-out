package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;


/**
 * 套餐菜品关系(SetmealDish)表数据库访问层
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:52:47
 */
@Mapper
public interface SetmealDishMapper {
    //修改菜品套餐关系
    public void updateSetmealDish(SetmealDish setmealDish);

    //根据套餐id删除对应数据
    public void deleteSetmealDishById(Long setmealId);

    //新增套餐菜品关系
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public Integer insertSetmealDish(SetmealDish setmealDish);

    //根据套餐id查询菜品
    public List<SetmealDish> selectSetmealDishById(Long setmealId);
}

