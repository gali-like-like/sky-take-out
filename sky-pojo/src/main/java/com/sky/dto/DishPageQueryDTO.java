package com.sky.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class DishPageQueryDTO implements Serializable {

    // 分类id，非必需
    private Integer categoryId;

    // 菜品名称，非必需
    @Size(max = 100, message = "菜品名称长度不能超过100个字符")
    private String name;

    // 页码，必需，且必须 >= 1
    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码必须大于或等于1")
    private Integer page;

    // 每页记录数，必需，且必须 >= 1
    @NotNull(message = "每页记录数不能为空")
    @Min(value = 1, message = "每页记录数必须大于或等于1")
    private Integer pageSize;

    // 分类状态，非必需
    private Integer status;

}
