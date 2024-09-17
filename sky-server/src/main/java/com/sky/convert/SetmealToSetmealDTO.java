package com.sky.convert;

import com.sky.dto.SetmealDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;

import java.util.List;

public class SetmealToSetmealDTO {
    static  public SetmealDTO conversionDTO(Setmeal setmeal, List<SetmealDish> setmealDishList) {
        SetmealDTO setmealDTO = new SetmealDTO();
        setmealDTO.setId(setmeal.getId());
        setmealDTO.setName(setmeal.getName());
        setmealDTO.setPrice(setmeal.getPrice());
        setmealDTO.setDescription(setmeal.getDescription());
        setmealDTO.setStatus(setmeal.getStatus());
        setmealDTO.setImage(setmeal.getImage());
        setmealDTO.setCategoryId(setmeal.getCategoryId());
        setmealDTO.setSetmealDishes(setmealDishList);
        return setmealDTO;
    }

}
