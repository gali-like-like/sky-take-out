package com.sky.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class BaseShoppingCart {
    public String name;//商品名称
    public String image;//商品图片
    public BigDecimal price;//商品价格
    public Long id;//商品id
    public LocalDateTime createTime;//创建时间
    public Integer number;//商品数量
    BaseShoppingCart(Long id, String name, String image, BigDecimal price,
                     Integer number, LocalDateTime createTime) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.number = number;
        this.createTime = createTime;
    }

}
