package com.sky.orther;

import com.sky.entity.Dish;
import com.sky.entity.SetmealDish;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealDishMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenerateData {

    @Resource
    private DishMapper dishMapper;

    @Resource
    private SetmealDishMapper setmealDishMapper;

    // 根据套餐菜品表的菜品id，填充套餐菜品表的菜品名称、价格、份数
    public void generateData() {
        List<SetmealDish> setmealDishes = setmealDishMapper.queryAll();
        List<SetmealDish> collect = setmealDishes.stream()
                .peek(setmealDish -> {
                    Dish dish = dishMapper.queryById(setmealDish.getDishId());
                    if (dish == null) {
                        return;
                    }
                    setmealDish.setName(dish.getName());
                    setmealDish.setPrice(dish.getPrice());
                    setmealDish.setCopies(1);
                })
                .collect(Collectors.toList());
        collect.forEach(setmealDishMapper::update);
    }
}
