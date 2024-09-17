package com.sky.service.impl;

import com.sky.entity.SetmealDish;
import com.sky.mapper.SetmealDishMapper;
import com.sky.service.SetmealDishService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 套餐菜品关系(SetmealDish)表服务实现类
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:42:02
 */
@Service("setmealDishService")
public class SetmealDishServiceImpl implements SetmealDishService {
    @Resource
    private SetmealDishMapper setmealDishMapper;

    //修改菜品套餐关系
    @Override
    public void updateSetmealDish(SetmealDish setmealDish) {
        setmealDishMapper.updateSetmealDish(setmealDish);
    }

    //根据套餐id删除对应数据
    @Override
    public void deleteSetmealDishById(Long setmealId) {
        setmealDishMapper.deleteSetmealDishById(setmealId);
    }

    //新增套餐菜品关系
    @Override
    public Integer insertSetmealDish(SetmealDish setmealDish) {
        Integer result = setmealDishMapper.insertSetmealDish(setmealDish);
        return result;
    }

    //根据套餐id查询菜品
    @Override
    public List<SetmealDish> selectSetmealDishById(Long setmealId) {
        return setmealDishMapper.selectSetmealDishById(setmealId);
    }
}
