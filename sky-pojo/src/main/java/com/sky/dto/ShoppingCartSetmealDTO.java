package com.sky.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class ShoppingCartSetmealDTO extends BaseShoppingCart {
    private List<ShoppingCartDishDTO> dishDTOS;
    public ShoppingCartSetmealDTO(Long id, String name, String image, BigDecimal price,
                                  Integer number, LocalDateTime createTime,List<ShoppingCartDishDTO> dishDTOS) {
        super(id,name,image,price,number,createTime);
        this.dishDTOS = dishDTOS;
    }
    public ShoppingCartSetmealDTO(Long id, String name, String image, BigDecimal price,
                                  Integer number, LocalDateTime createTime) {
        super(id,name,image,price,number,createTime);
    }

}
