package com.sky.dto;

import com.sky.entity.DishFlavor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class DishDTO implements Serializable {

    @NotNull(message = "categoryId 是必需的")
    @Min(value = 1, message = "categoryId 必须是大于0的整数")
    private Long categoryId;

    @Size(max = 255, message = "description 长度不能超过255个字符")
    private String description;

    @Valid  // 这将触发对 flavors 列表中每个元素的验证
    private List<DishFlavor> flavors;

    @NotNull(message = "id 是必需的")
    @Min(value = 1, message = "id 必须是大于0的整数")
    private Long id;

    @NotBlank(message = "image 是必需的")
    private String image;

    @NotBlank(message = "name 是必需的")
    private String name;

    @NotNull(message = "price 是必需的")
    @DecimalMin(value = "0.0", inclusive = false, message = "price 必须大于0")
    private BigDecimal price;

    private Integer status;


}
