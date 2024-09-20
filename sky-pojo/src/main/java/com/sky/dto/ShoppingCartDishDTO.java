package com.sky.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ShoppingCartDishDTO extends BaseShoppingCart{
    public String flavor;//菜品口味
    public ShoppingCartDishDTO(Long id, String name, String image, BigDecimal price,
                               Integer number, LocalDateTime createTime,String flavor) {
        super(id,name,image,price,number,createTime);
        this.flavor = flavor;
    }
}
