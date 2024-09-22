package com.sky.dto;

import com.sky.entity.SetmealDish;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class SetmealDTO implements Serializable {

    private Long id;

    //分类id
    private Long categoryId;

    //套餐名称
    @NotBlank(message = "套餐名称不能为空")
    private String name;

    //套餐价格
    @DecimalMin(value = "0", message = "套餐价格不能小于0")
    private BigDecimal price;

    //状态 0:停用 1:启用
    private Integer status;

    //描述信息
    private String description;

    //图片
    private String image;

    //套餐菜品关系
    private List<SetmealDish> setmealDishes = new ArrayList<>();

}
