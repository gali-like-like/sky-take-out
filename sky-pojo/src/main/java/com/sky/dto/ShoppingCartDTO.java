package com.sky.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ShoppingCartDTO implements Serializable {
    @NotNull(message = "用户编号不能为空")
    private Long userId;//用户id
    private Long dishId;//菜品id和套餐id互斥
    private Long setmealId;//菜品id和套餐id互斥
    private Integer number;//商品数量
}
